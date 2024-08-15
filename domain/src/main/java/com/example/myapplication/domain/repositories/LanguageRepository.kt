package com.example.myapplication.domain.repositories

import com.example.myapplication.domain.models.LanguageAndWordsModel
import com.example.myapplication.domain.models.LanguageModel
import kotlinx.coroutines.flow.Flow

interface LanguageRepository {

    suspend fun createLanguage(item: LanguageModel): Result<Boolean>
    suspend fun readLanguagesWithWords(): Result<Flow<List<LanguageAndWordsModel>>>
    suspend fun readLanguages(): Result<Flow<List<LanguageModel>>>
    suspend fun updateLanguage(item: LanguageModel): Result<Boolean>
    suspend fun deleteLanguage(item: LanguageModel): Result<Boolean>
    suspend fun searchLanguage(req: String): Result<Flow<List<LanguageModel>>>
    suspend fun readLanguageWithWordsById(id: Int): Result<Flow<LanguageAndWordsModel>>

}