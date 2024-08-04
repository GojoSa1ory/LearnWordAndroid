package com.example.myapplication.data.repositories

import com.example.myapplication.data.interfaces.WordDao
import com.example.myapplication.data.model.errors.DatabaseErrors
import com.example.myapplication.domain.models.WordModel
import com.example.myapplication.domain.repositories.WordRepository
import kotlinx.coroutines.flow.Flow

class WordRepositoryImpl(private val service: WordDao): WordRepository {

    override suspend fun create(item: WordModel): Result<Boolean> {
        return try {
            service.create(item)
            Result.success(true)
        } catch(e: Exception) {
            Result.failure(DatabaseErrors.CreateFailed)
        }
    }

    override suspend fun read(): Result<Flow<List<WordModel>>> {
        return try {
            val words = service.read()
            Result.success(words)
        } catch(e: Exception) {
            Result.failure(DatabaseErrors.ReadFailed)
        }
    }

    override suspend fun update(item: WordModel): Result<Boolean> {
        return try {
            service.update(item)
            Result.success(true)
        } catch(e: Exception) {
            Result.failure(DatabaseErrors.UpdateFailed)
        }
    }

    override suspend fun delete(item: WordModel): Result<Boolean> {
        return try {
            service.delete(item)
            Result.success(true)
        } catch(e: Exception) {
            Result.failure(DatabaseErrors.DeleteFailed)
        }
    }

    override suspend fun search(query: String): Result<Flow<List<WordModel>>> {
        return try {
            val words = service.search(query)
            Result.success(words)
        } catch(e: Exception) {
            Result.failure(DatabaseErrors.ReadFailed)
        }
    }


}