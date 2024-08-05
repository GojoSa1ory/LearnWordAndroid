package com.example.myapplication.data.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.myapplication.data.model.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao: BaseDAO<WordEntity> {
    @Query("""
        SELECT * FROM Words
        WHERE (:request IS NULL OR :request = '') 
            OR main_word LIKE '%' || :request || '%' 
            OR translated_word LIKE '%' || :request || '%'
    """)
    fun search (request: String): Flow<List<WordEntity>>

    @Query("SELECT * FROM Words")
    override fun read(): Flow<List<WordEntity>>
}