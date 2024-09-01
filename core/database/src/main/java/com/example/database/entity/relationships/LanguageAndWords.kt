package com.example.database.entity.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.example.database.entity.LanguageEntity
import com.example.database.entity.WordEntity

data class LanguageAndWords(

    @Embedded val language: LanguageEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "lang_id_fk"
    )
    val words: List<WordEntity>
) {
    companion object
}
