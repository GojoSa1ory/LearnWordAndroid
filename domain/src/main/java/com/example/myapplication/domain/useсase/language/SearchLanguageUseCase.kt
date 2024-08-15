package com.example.myapplication.domain.useсase.language

import com.example.myapplication.domain.models.LanguageModel
import com.example.myapplication.domain.repositories.LanguageRepository
import kotlinx.coroutines.flow.Flow

class SearchLanguageUseCase(
    private val rep: LanguageRepository
) {
    suspend fun execute(req: String): Result<Flow<List<LanguageModel>>> {
        return rep.searchLanguage(req)
    }
}