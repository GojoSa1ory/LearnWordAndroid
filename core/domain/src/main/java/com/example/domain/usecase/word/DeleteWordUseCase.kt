package com.example.domain.usecase.word

import com.example.domain.repository.WordRepository
import com.example.domain.model.WordModel

class DeleteWordUseCase(
    private val rep: WordRepository
) {
    suspend fun invoke(word: WordModel): Result<Boolean> {
        return rep.delete(word)
    }
}