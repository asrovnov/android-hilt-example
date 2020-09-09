package android.hilt.example.data.backend

import android.hilt.example.data.backend.dto.DtoToEntityMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <E, D : DtoToEntityMapper<E>> Flow<D>.mapToEntity(): Flow<E> {
    return this.map { it.toEntity() }
}