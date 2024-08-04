package com.example.myapplication.domain.repositories

import com.example.myapplication.domain.models.WordModel
import kotlinx.coroutines.flow.Flow

interface WordRepository {
    suspend fun create(item: WordModel): Result<Boolean>
    suspend fun read(): Result<Flow<List<WordModel>>>
    suspend fun update(item: WordModel): Result<Boolean>
    suspend fun delete(item: WordModel): Result<Boolean>
    suspend fun search(query: String): Result<Flow<List<WordModel>>>
}