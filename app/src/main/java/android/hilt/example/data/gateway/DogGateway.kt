package android.hilt.example.data.gateway

import android.hilt.example.data.backend.Api
import android.hilt.example.data.backend.mapToEntity
import android.hilt.example.domain.Dog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DogGateway(private val api: Api) {

    fun getRandomDog(): Flow<Dog> {
        return flow {
            try {
                emit(api.getRandomDog())
            } catch (e: Exception) {
                throw Exception()
            }
        }
            .mapToEntity()
    }
}