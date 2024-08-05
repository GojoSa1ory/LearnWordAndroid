package com.example.myapplication.data.interfaces

import androidx.room.Query
import com.example.myapplication.domain.models.WordModel
import kotlinx.coroutines.flow.Flow

interface WordDao: BaseDAO<WordModel> {
    @Query("SELECT * FROM Words WHERE main_word LIKE :request OR translated_word LIKE :request")
    suspend fun search (request: String): Flow<List<WordModel>>

    @Query("SELECT * FROM Words")
    override suspend fun read(): Flow<List<WordModel>>
}