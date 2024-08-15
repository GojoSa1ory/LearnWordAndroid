package com.example.myapplication.data.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.myapplication.data.entities.LanguageEntity
import com.example.myapplication.data.entities.relationships.LanguageAndWordsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LanguageDao: BaseDAO<LanguageAndWordsEntity, LanguageEntity> {

    @Transaction
    @Query("""
       SELECT * FROM Languages 
    """)
    override fun read(): Flow<List<LanguageAndWordsEntity>>

    @Query("""
         SELECT * FROM Languages
    """)
    fun readOnlyLanguages(): Flow<List<LanguageEntity>>

    @Transaction
    @Query("""
        SELECT * FROM Languages
        WHERE langId == :id
    """)
    fun getLanguageWithWordsById(id: Int): Flow<LanguageAndWordsEntity>

    @Query("""
        SELECT * FROM Languages
        WHERE (:req IS NULL OR :req = '') 
            OR language_name LIKE '%' || :req || '%'
    """)
    fun searchLanguage(req: String): Flow<List<LanguageEntity>>
}