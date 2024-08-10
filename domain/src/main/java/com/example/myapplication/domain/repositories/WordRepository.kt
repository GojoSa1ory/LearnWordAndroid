package com.example.myapplication.domain.repositories

import com.example.myapplication.domain.models.LanguageAndWordsModel
import com.example.myapplication.domain.models.WordAndLanguageModel
import com.example.myapplication.domain.models.WordModel
import kotlinx.coroutines.flow.Flow

interface WordRepository {
    suspend fun create(item: WordModel): Result<Long>
    suspend fun read(): Result<Flow<List<WordAndLanguageModel>>>
    suspend fun update(item: WordModel): Result<Int>
    suspend fun delete(item: WordModel): Result<Int>
    suspend fun search(query: String): Result<Flow<List<WordAndLanguageModel>>>
}