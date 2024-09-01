package com.example.domain.usecase.language

import com.example.domain.model.LanguageAndWordsModel
import com.example.domain.repository.LanguageRepository

class GetLanguageAndWordsByIdUseCase(
    private val rep: LanguageRepository
) {
    suspend fun invoke(id: Int): Result<LanguageAndWordsModel> {
        return rep.readWithWordsById(id)
    }
}