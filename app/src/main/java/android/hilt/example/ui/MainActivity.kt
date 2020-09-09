package android.hilt.example.ui

import android.hilt.example.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm.error bind {
            Toast.makeText(this, resources.getString(R.string.error_message_text), Toast.LENGTH_SHORT).show()
        }

        vm.imageUrl bind { ImageUrl ->
            Glide.with(this)
                .load(ImageUrl)
                .placeholder(R.drawable.bg_image)
                .centerCrop()
                .into(image)
        }

        downloadImageButton.setOnClickListener {
            vm.setImageUrl()
        }

    }

    private infix fun <T> Flow<T>.bind(block: (T) -> Unit) {
        lifecycleScope.launchWhenStarted {
            collect { block(it) }
        }
    }

    private infix fun <T> Command<T>.bind(block: (T) -> Unit) {
        lifecycleScope.launchWhenStarted {
            while (true) {
                block(this@bind.receive())
            }
        }
    }
}