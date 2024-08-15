package com.example.myapplication.domain.useсase.language

import com.example.myapplication.domain.models.LanguageModel
import com.example.myapplication.domain.repositories.LanguageRepository
import kotlinx.coroutines.flow.Flow

class GetLanguagesUseCase(
    private val rep: LanguageRepository
) {
    suspend fun execute(): Result<Flow<List<LanguageModel>>> {
        return rep.readLanguages()
    }
}