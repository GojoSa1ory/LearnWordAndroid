package com.example.myapplication.domain.useсase.wordusecase

import com.example.myapplication.domain.models.WordModel
import com.example.myapplication.domain.repositories.WordRepository


class CreateWordUseCase(private val wordRep: WordRepository) {
    suspend fun execute(item: WordModel): Result<Boolean> {
        return wordRep.create(item)
    }
}