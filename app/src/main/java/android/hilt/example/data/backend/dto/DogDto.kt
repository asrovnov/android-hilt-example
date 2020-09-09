package android.hilt.example.data.backend.dto

import android.hilt.example.domain.Dog
import com.google.gson.annotations.SerializedName

data class DogDto(
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: String
) : DtoToEntityMapper<Dog> {

    override fun toEntity() = Dog(message, status)
}