package com.example.domain.repository

import com.example.domain.model.LanguageModel
import kotlinx.coroutines.flow.Flow

interface LanguageRepository {
    suspend fun create(item: LanguageModel): Result<Boolean>
    fun read(): Result<Flow<List<LanguageModel>>>
    fun readWithWords(): Result<Flow<List<LanguageModel>>>
    suspend fun update(item: LanguageModel): Result<Boolean>
    suspend fun delete(item: LanguageModel): Result<Boolean>
    fun search(req: String): Result<Flow<List<LanguageModel>>>
}