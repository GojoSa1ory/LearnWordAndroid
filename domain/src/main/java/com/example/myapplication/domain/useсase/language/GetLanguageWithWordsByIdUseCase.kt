package com.example.myapplication.domain.useсase.language

import com.example.myapplication.domain.models.LanguageAndWordsModel
import com.example.myapplication.domain.repositories.LanguageRepository
import kotlinx.coroutines.flow.Flow

class GetLanguageWithWordsByIdUseCase (
    private val rep: LanguageRepository
) {
    suspend fun execute(id: Int): Result<Flow<LanguageAndWordsModel>> {
        return rep.readLanguageWithWordsById(id)
    }
}