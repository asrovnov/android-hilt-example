package android.hilt.example.ui

import android.hilt.example.data.gateway.DogGateway
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainViewModel @ViewModelInject constructor(
    private val dogGateway: DogGateway
) : ViewModel() {

    private val coroutineScope = viewModelScope

    val imageUrl: MutableStateFlow<String> = MutableStateFlow("")
    val error = command<Exception>()

    init {
        imageUrl.apply {
            setImageUrl()
        }
    }

    fun setImageUrl() {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                dogGateway.getRandomDog()
                    .map { it.imageUrl }
                    .collect { url ->
                        imageUrl.value = url
                    }
            } catch (e: Exception) {
                error.send(e)
            }
        }
    }
}