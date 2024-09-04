package com.example.data.repository

import com.example.database.daos.WordDao
import com.example.database.entity.WordEntity
import com.example.data.mapper.mapToEntity
import com.example.data.mapper.mapToModel
import com.example.domain.model.WordModel
import com.example.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WordRepositoryImpl(
    private val dao: WordDao
): WordRepository {
    override suspend fun create(word: WordModel): Result<Boolean> {
        return try {

            val word = WordModel.mapToEntity(word)

            val res = dao.create(word)

            Result.success(true)

        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    override fun readWithLanguage(): Result<Flow<List<WordModel>>> {
        return try {
            val req = dao.readWithLanguages()

            val res = req.map { data ->
                data.map {
                    WordEntity.mapToModel(it.word, it.language.languageName)
                }
            }

            Result.success(res)
        } catch (ex: Exception) {
            return Result.failure(ex)
        }
    }

    override fun readWordsByLangId(langId: Int): Result<Flow<List<WordModel>>> {
        return  try {

            val req = dao.readWordsByLanguage(langId)

            val res = req.map { req ->
                req.map { data ->
                    WordEntity.mapToModel(data, languageName = "")
                }
            }

            Result.success(res)

        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    override suspend fun getById(): Result<WordModel> {
        TODO("Not yet implemented")
    }

    override suspend fun update(word: WordModel): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(word: WordModel): Result<Boolean> {

        return try {
            val word = WordModel.mapToEntity(word)
            val req = dao.delete(word)

            Result.success(true)
        } catch (ex: Exception) {
            Result.failure(ex)
        }

    }

    override fun search(req: String): Result<Flow<List<WordModel>>> {

        return try {
            val req = dao.search(req)

            val res = req.map { data ->
                data.map {
                    WordEntity.mapToModel(it.word, it.language.languageName)
                }
            }

            Result.success(res)

        } catch(ex: Exception) {
            Result.failure(ex)
        }
    }

}