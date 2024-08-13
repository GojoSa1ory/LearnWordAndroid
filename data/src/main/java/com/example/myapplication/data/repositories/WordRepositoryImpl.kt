package com.example.myapplication.data.repositories

import com.example.myapplication.data.daos.WordDao
import com.example.myapplication.data.entities.relationships.WordAndLanguageEntity
import com.example.myapplication.data.entities.errors.DatabaseErrors
import com.example.myapplication.data.mapper.mapToEntity
import com.example.myapplication.data.mapper.mapToModel
import com.example.myapplication.domain.models.WordAndLanguageModel
import com.example.myapplication.domain.models.WordModel
import com.example.myapplication.domain.repositories.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class WordRepositoryImpl(private val service: WordDao):
    WordRepository {

    override suspend fun create(item: WordModel): Result<Long> {
        return withContext(Dispatchers.IO) {
            try {
                val word = WordModel.mapToEntity(item, isForCreate = true)
                val res = service.create(word)
                Result.success(res)
            } catch (e: Exception) {
                Result.failure(DatabaseErrors.CreateFailed(message = e.localizedMessage ?: "Create failed"))
            }
        }
    }

    override suspend fun read(): Result<Flow<List<WordAndLanguageModel>>> {
        return try {
            val res = service.read()

            val languageAndWordsModel = res.map { data ->
                data.map { t ->
                    WordAndLanguageEntity.mapToModel(t)
                }
            }

            Result.success(languageAndWordsModel)
        } catch(e: Exception) {
            Result.failure(DatabaseErrors.ReadFailed(message = e.localizedMessage ?: "Read failed"))
        }
    }

    override suspend fun update(item: WordModel): Result<Int> {
        return try {

            val word = WordModel.mapToEntity(item, isForCreate = false)

            val res = service.update(word)
            Result.success(res)
        } catch(e: Exception) {
            Result.failure(DatabaseErrors.UpdateFailed(message = e.localizedMessage ?: "Update failed"))
        }
    }

    override suspend fun delete(item: WordModel): Result<Int> {
        return withContext(Dispatchers.IO) {
            try {
                val word = WordModel.mapToEntity(item, isForCreate = false)
                val res = service.delete(word)
                Result.success(res)
            } catch(e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun search(query: String): Result<Flow<List<WordAndLanguageModel>>> {
        return try {
            val res = service.search(query)

            val languageAndWordsModel = res.map { data ->
                data.map { langWords ->
                    WordAndLanguageEntity.mapToModel(langWords)
                }
            }

            Result.success(languageAndWordsModel)
        } catch(e: Exception) {
            Result.failure(DatabaseErrors.ReadFailed(message = e.localizedMessage ?: "Search failed"))
        }
    }


}
