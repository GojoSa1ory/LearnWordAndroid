package com.example.database.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.database.entity.LanguageEntity
import com.example.database.entity.relationships.LanguageAndWords
import kotlinx.coroutines.flow.Flow

@Dao
interface LanguageDao: BaseCRUDao<LanguageEntity> {

    @Query("""
        SELECT * FROM Languages
        WHERE (:req IS NULL OR :req == '')
        OR language_name LIKE '%' || :req || '%'
    """)
    fun search(req: String): Flow<LanguageEntity>

    @Query("""
        SELECT * FROM Languages
    """)
    override fun read(): Flow<List<LanguageEntity>>

    @Transaction
    @Query("""
        SELECT * FROM Languages
    """)
    fun readLanguagesWithWords(): Flow<List<LanguageAndWords>>

    @Transaction
    @Query("""
        SELECT * FROM Languages
        WHERE id == :id
    """)
    suspend fun readLanguageWithWordsById (id: Int): LanguageAndWords

    @Query("DELETE FROM Words WHERE lang_id_fk = :languageId")
    suspend fun deleteWordsByLanguage(languageId: Int)

    @Transaction
    suspend fun deleteLanguageAndWords(item: LanguageEntity) {
        delete(item)
        deleteWordsByLanguage(item.id)
    }
}