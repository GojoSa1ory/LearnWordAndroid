package com.example.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import com.example.database.entity.WordEntity
import com.example.database.entity.relationships.WordAndLanguages
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao: BaseCRUDao<WordEntity> {

    @Transaction
    @Query("""
        SELECT * FROM Words
        WHERE (:req IS NULL OR :req == '')
        OR mainWord LIKE '%' || :req || '%' 
        OR translated_word LIKE '%' || :req || '%'
    """)
    fun search(req: String): Flow<List<WordAndLanguages>>

    @Query("SELECT * FROM Words")
    override fun read(): Flow<List<WordEntity>>

    @Transaction
    @Query("SELECT * FROM Words")
    fun readWithLanguages(): Flow<List<WordAndLanguages>>

    @Query("""
        SELECT * FROM Words
        WHERE lang_id_fk == :languageId
    """)
    fun readWordsByLanguage(languageId: Int): Flow<List<WordEntity>>
}