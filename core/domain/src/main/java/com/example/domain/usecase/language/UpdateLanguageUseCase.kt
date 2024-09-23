package com.example.domain.usecase.language

import com.example.domain.model.LanguageModel
import com.example.domain.repository.LanguageRepository

class UpdateLanguageUseCase(
    private val rep: LanguageRepository
) {
    suspend fun invoke (word: LanguageModel): Result<Boolean> {
        return rep.update(word)
    }
}