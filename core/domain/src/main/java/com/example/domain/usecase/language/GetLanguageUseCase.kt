package com.example.domain.usecase.language

import com.example.domain.model.LanguageModel
import com.example.domain.repository.LanguageRepository
import kotlinx.coroutines.flow.Flow

class GetLanguageUseCase(
    private val rep: LanguageRepository
) {
    suspend fun invoke(): Result<Flow<List<LanguageModel>>> {
        return rep.read()
    }
}