package com.example.myapplication.domain.useсase.wordusecase

import com.example.myapplication.domain.models.WordModel
import com.example.myapplication.domain.repositories.WordRepository

class UpdateWordUseCase(private val rep: WordRepository) {
    suspend fun execute (item: WordModel): Result<Boolean> {
        return rep.update(item)
    }
}