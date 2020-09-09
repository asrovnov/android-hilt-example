package android.hilt.example.data.backend.dto

interface DtoToEntityMapper<E> {
    fun toEntity(): E
}