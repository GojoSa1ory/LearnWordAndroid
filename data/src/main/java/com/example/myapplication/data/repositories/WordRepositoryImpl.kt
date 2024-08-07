package com.example.myapplication.data.repositories

import com.example.myapplication.data.daos.WordDao
import com.example.myapplication.data.model.errors.DatabaseErrors
import com.example.myapplication.data.mapper.WordMapper
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
                val word = WordMapper().mapToEntityForCreate(item)
                val res = service.create(word)
                Result.success(res)
            } catch (e: Exception) {
                Result.failure(DatabaseErrors.CreateFailed)
            }
        }
    }

    override suspend fun read(): Result<Flow<List<WordModel>>> {
        return try {
            val res = service.read()

            val words: Flow<List<WordModel>> = res.map { data ->
                data.map { word ->
                    WordMapper().mapToModel(word)
                }
            }

            Result.success(words)
        } catch(e: Exception) {
            Result.failure(DatabaseErrors.ReadFailed)
        }
    }

    override suspend fun update(item: WordModel): Result<Int> {
        return try {

            val word = WordMapper().mapToEntity(item)

            val res = service.update(word)
            Result.success(res)
        } catch(e: Exception) {
            Result.failure(DatabaseErrors.UpdateFailed)
        }
    }

    override suspend fun delete(item: WordModel): Result<Int> {
        return withContext(Dispatchers.IO) {
            try {
                val word = WordMapper().mapToEntity(item)
                val res = service.delete(word)
                Result.success(res)
            } catch(e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun search(query: String): Result<Flow<List<WordModel>>> {
        return try {
            val res = service.search(query)

            val words = res.map { data ->
                data.map { word ->
                    WordMapper().mapToModel(word)
                }
            }

            Result.success(words)
        } catch(e: Exception) {
            Result.failure(DatabaseErrors.ReadFailed)
        }
    }


}