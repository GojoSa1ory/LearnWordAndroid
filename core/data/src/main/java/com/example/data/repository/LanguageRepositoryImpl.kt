package com.example.data.repository

import com.example.data.mapper.mapToEntity
import com.example.data.mapper.mapToModel
import com.example.database.daos.LanguageDao
import com.example.database.entity.LanguageEntity
import com.example.database.entity.relationships.LanguageAndWords
import com.example.domain.model.LanguageAndWordsModel
import com.example.domain.model.LanguageModel
import com.example.domain.repository.LanguageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LanguageRepositoryImpl(
    private val dao: LanguageDao
): LanguageRepository {
    override suspend fun create(item: LanguageModel): Result<Boolean> {
        return try {

            val lang = LanguageModel.mapToEntity(item)

            val req = dao.create(lang)

            Result.success(true)

        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    override fun read(): Result<Flow<List<LanguageModel>>> {
        return try {

            val req = dao.read()

            val response = req.map { data ->
                data.map {
                    LanguageEntity.mapToModel(it)
                }
            }

            Result.success(response)

        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    override fun readWithWords(): Result<Flow<List<LanguageModel>>> {
        TODO("Not yet implemented")
    }

    override suspend fun readWithWordsById(id: Int): Result<LanguageAndWordsModel> {
        return try {

            val req = dao.readLanguageWithWordsById(id)

            val res = LanguageAndWords.mapToModel(req)

            Result.success(res)

        } catch(ex: Exception) {
            Result.failure(ex)
        }
    }

    override suspend fun update(item: LanguageModel): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(item: LanguageModel): Result<Boolean> {
        return try {

            val lang = LanguageModel.mapToEntity(item)

            val req = dao.deleteLanguageAndWords(lang)

            Result.success(true)

        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    override fun search(req: String): Result<Flow<List<LanguageModel>>> {
        return try {

            val request = dao.search(req)

            val res = request.map { res ->
                res.map {
                    LanguageEntity.mapToModel(it)
                }
            }

            Result.success(res)
        } catch(ex: Exception) {
            Result.failure(ex)
        }
    }
}