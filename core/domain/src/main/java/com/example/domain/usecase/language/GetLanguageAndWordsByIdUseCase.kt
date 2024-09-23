package com.example.domain.usecase.language

import com.example.domain.model.LanguageAndWordsModel
import com.example.domain.repository.LanguageRepository
import kotlinx.coroutines.flow.Flow

class GetLanguageAndWordsByIdUseCase(
    private val rep: LanguageRepository
) {
    suspend fun invoke(id: Int): Result<Flow<LanguageAndWordsModel>> {
        return rep.readWithWordsById(id)
    }
}