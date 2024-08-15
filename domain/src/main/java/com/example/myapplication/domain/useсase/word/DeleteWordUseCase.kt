package com.example.myapplication.domain.useсase.word

import com.example.myapplication.domain.models.WordModel
import com.example.myapplication.domain.repositories.WordRepository


class DeleteWordUseCase(
    private val rep: WordRepository
) {
    suspend fun execute (item: WordModel): Result<Int> {
        return rep.delete(item)
    }
}