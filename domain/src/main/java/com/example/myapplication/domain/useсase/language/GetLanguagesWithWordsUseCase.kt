package com.example.myapplication.domain.useсase.language

import com.example.myapplication.domain.models.LanguageAndWordsModel
import com.example.myapplication.domain.repositories.LanguageRepository
import kotlinx.coroutines.flow.Flow


class GetLanguagesWithWordsUseCase(
    private val repository: LanguageRepository
) {
    suspend fun execute(): Result<Flow<List<LanguageAndWordsModel>>> {
        return repository.readLanguagesWithWords()
    }
}