package com.example.domain.repository

import com.example.domain.model.WordModel
import kotlinx.coroutines.flow.Flow

interface WordRepository {
    suspend fun create(word: WordModel): Result<Boolean>
    fun readWithLanguage(): Result<Flow<List<WordModel>>>
    fun readWordsByLangId(langId: Int): Result<Flow<List<WordModel>>>
    suspend fun getById(): Result<WordModel>
    suspend fun update(word: WordModel): Result<Boolean>
    suspend fun delete(word: WordModel): Result<Boolean>
    fun search(req: String): Result<Flow<List<WordModel>>>
}