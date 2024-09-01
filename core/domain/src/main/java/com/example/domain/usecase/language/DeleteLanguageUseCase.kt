package com.example.domain.usecase.language

import com.example.domain.model.LanguageModel
import com.example.domain.repository.LanguageRepository

class DeleteLanguageUseCase(
    private val rep: LanguageRepository
) {
    suspend fun invoke(item: LanguageModel): Result<Boolean> {
        return rep.delete(item)
    }
}