package com.example.myapplication.domain.useCase.wordUseCase

import com.example.myapplication.data.model.ServiceResponse
import com.example.myapplication.domain.entities.WordEntity
import com.example.myapplication.domain.repositories.WordRepository

class SearchWordUseCase(private val rep: WordRepository) {
    fun execute (req: String): ServiceResponse<List<WordEntity>> {
        return rep.search(req)
    }
}