package com.example.myapplication.domain.useCase.wordUseCase

import com.example.myapplication.data.model.ServiceResponse
import com.example.myapplication.domain.entities.WordEntity
import com.example.myapplication.domain.repositories.WordRepository

class GetWordsUseCase(private val wordRep: WordRepository) {
    fun execute (): ServiceResponse<List<WordEntity>> {
        return wordRep.read()
    }
}