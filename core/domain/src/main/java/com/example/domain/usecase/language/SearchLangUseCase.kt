package com.example.domain.usecase.language

import com.example.domain.model.LanguageModel
import com.example.domain.repository.LanguageRepository
import kotlinx.coroutines.flow.Flow

class SearchLangUseCase(
    private val rep: LanguageRepository
) {
    suspend fun invoke(req: String): Result<Flow<List<LanguageModel>>> {
        return rep.search(req)
    }
}