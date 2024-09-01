package com.example.domain.usecase.language

import com.example.domain.model.LanguageModel
import com.example.domain.repository.LanguageRepository

class CreateLanguageUseCase(
    private val rep: LanguageRepository
) {
    suspend fun invoke(language: LanguageModel): Result<Boolean> {
        return rep.create(language)
    }
}