package com.example.domain.usecase.word

import com.example.domain.repository.WordRepository
import com.example.domain.model.WordModel
import kotlinx.coroutines.flow.Flow

class GetWordWithLanguageUseCase(
    private val rep: WordRepository
) {
    fun invoke(): Result<Flow<List<WordModel>>> {
        return rep.readWithLanguage()
    }
}