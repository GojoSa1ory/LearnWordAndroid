package com.example.domain.usecase.word

import com.example.domain.repository.WordRepository
import com.example.domain.model.WordModel
import kotlinx.coroutines.flow.Flow

class SearchWordUseCase(
    private val rep: WordRepository
) {
    fun invoke(req: String): Result<Flow<List<WordModel>>> {
        return rep.search(req)
    }
}