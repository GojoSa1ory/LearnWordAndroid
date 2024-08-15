package com.example.myapplication.data.repositories

import com.example.myapplication.data.daos.LanguageDao
import com.example.myapplication.data.entities.LanguageEntity
import com.example.myapplication.data.entities.relationships.LanguageAndWordsEntity
import com.example.myapplication.data.mapper.mapToEntity
import com.example.myapplication.data.mapper.mapToModel
import com.example.myapplication.domain.models.LanguageAndWordsModel
import com.example.myapplication.domain.models.LanguageModel
import com.example.myapplication.domain.repositories.LanguageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LanguageRepositoryImpl(
    private val dao: LanguageDao
): LanguageRepository {
    override suspend fun createLanguage(item: LanguageModel): Result<Boolean> {
        return try {

            val lang = LanguageModel.mapToEntity(item)

            val res = dao.create(lang)

            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun readLanguagesWithWords(): Result<Flow<List<LanguageAndWordsModel>>> {
        return try {

            val response = dao.read()

            val result = response.map { res ->
                res.map { data ->
                    LanguageAndWordsEntity.mapToModel(data)
                }
            }

            Result.success(result)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    override suspend fun readLanguages(): Result<Flow<List<LanguageModel>>> {
        return try {

            val response = dao.readOnlyLanguages().map {
                it.map { lang ->
                    LanguageEntity.mapToModel(lang)
                }
            }

            Result.success(response)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    override suspend fun updateLanguage(item: LanguageModel): Result<Boolean> {
        return try {
            val req = LanguageModel.mapToEntity(item)

            dao.update(req)
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteLanguage(item: LanguageModel): Result<Boolean> {
        return try {
            val req = LanguageModel.mapToEntity(item)

            dao.delete(req)
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun searchLanguage(req: String): Result<Flow<List<LanguageModel>>> {
        return try {

            val request = dao.searchLanguage(req)

            val res = request.map { data ->
                data.map { lang ->
                    LanguageEntity.mapToModel(lang)
                }
            }

            Result.success(res)

        } catch(ex: Exception) {
            Result.failure(ex)
        }
    }


    override suspend fun readLanguageWithWordsById(id: Int): Result<Flow<LanguageAndWordsModel>> {
        return try {
            val req = dao.getLanguageWithWordsById(id)

            val res = req.map { res ->
                LanguageAndWordsEntity.mapToModel(res)
            }

            Result.success(res)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}