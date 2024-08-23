package com.example.domain.usecase

import com.example.domain.WordRepository
import com.example.domain.model.WordModel
import kotlinx.coroutines.flow.Flow

class GetWordWithLanguageUseCase(
    private val rep: WordRepository
) {
    fun invoke(): Result<Flow<List<WordModel>>> {
        return rep.readWithLanguage()
    }
}