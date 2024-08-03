package com.example.myapplication.domain.useCase.wordUseCase

import com.example.myapplication.data.model.ServiceResponse
import com.example.myapplication.domain.entities.WordEntity
import com.example.myapplication.domain.repositories.WordRepository

class CreateWordUseCase(private val wordRep: WordRepository) {
    fun execute(item: WordEntity): ServiceResponse<String> {
        return wordRep.create(item)
    }
}