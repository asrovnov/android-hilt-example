package android.hilt.example.data.backend

import android.hilt.example.data.backend.dto.DogDto
import retrofit2.http.GET

interface Api {

    @GET("breeds/image/random")
    suspend fun getRandomDog(): DogDto
}