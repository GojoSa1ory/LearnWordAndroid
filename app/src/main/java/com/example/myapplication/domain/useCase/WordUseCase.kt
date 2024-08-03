package com.example.myapplication.domain.useCase

import com.example.myapplication.data.model.ServiceResponse
import com.example.myapplication.data.model.WordModel
import com.example.myapplication.domain.entities.WordEntity
import com.example.myapplication.domain.repositories.WordRepository

class WordUseCase(private val wordRep: WordRepository) {

    fun create(item: WordEntity): ServiceResponse<String> {
        return wordRep.create(item)
    }

    fun read(): ServiceResponse<List<WordEntity>> {
        return wordRep.read()
    }

    fun update(item: WordEntity): ServiceResponse<String> {
        TODO("Not yet implemented")
    }

    fun delete(item: WordEntity): ServiceResponse<String> {
        TODO("Not yet implemented")
    }

    fun search(query: String): ServiceResponse<List<WordModel>> {
        TODO("Not yet implemented")
    }

}