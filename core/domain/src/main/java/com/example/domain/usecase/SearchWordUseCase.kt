package com.example.domain.usecase

import com.example.domain.WordRepository
import com.example.domain.model.WordModel
import kotlinx.coroutines.flow.Flow

class SearchWordUseCase(
    private val rep: WordRepository
) {
    fun invoke(req: String): Result<Flow<List<WordModel>>> {
        return rep.search(req)
    }
}