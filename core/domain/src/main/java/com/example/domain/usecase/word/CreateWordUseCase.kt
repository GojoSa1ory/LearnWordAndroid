package com.example.domain.usecase.word

import com.example.domain.model.WordModel
import com.example.domain.repository.WordRepository

class CreateWordUseCase(
    private val rep: WordRepository
) {
    suspend fun invoke(item: WordModel): Result<Boolean> {
        return rep.create(item)
    }
}