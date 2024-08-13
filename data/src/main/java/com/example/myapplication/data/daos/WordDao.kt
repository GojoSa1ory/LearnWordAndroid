package com.example.myapplication.data.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.myapplication.data.entities.relationships.WordAndLanguageEntity
import com.example.myapplication.data.entities.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao: BaseDAO<WordAndLanguageEntity, WordEntity> {

    @Transaction
    @Query("""
        SELECT * FROM Words
        WHERE (:request IS NULL OR :request = '') 
            OR main_word LIKE '%' || :request || '%' 
            OR translated_word LIKE '%' || :request || '%'
    """)
    fun search (request: String): Flow<List<WordAndLanguageEntity>>

    @Transaction
    @Query("SELECT * FROM Words")
    override fun read(): Flow<List<WordAndLanguageEntity>>
}