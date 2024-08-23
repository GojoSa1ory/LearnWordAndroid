package com.example.domain.usecase

import com.example.domain.WordRepository
import com.example.domain.model.WordModel

class DeleteWordUseCase(
    private val rep: WordRepository
) {
    suspend fun invoke(word: WordModel): Result<Boolean> {
        return rep.delete(word)
    }
}