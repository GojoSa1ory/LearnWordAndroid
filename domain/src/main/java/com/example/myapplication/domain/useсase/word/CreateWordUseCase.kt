package com.example.myapplication.domain.useсase.word

import com.example.myapplication.domain.models.WordModel
import com.example.myapplication.domain.repositories.WordRepository


class CreateWordUseCase(private val wordRep: WordRepository) {
    suspend fun execute(item: WordModel): Result<Long> {
        return wordRep.create(item)
    }
}