package com.example.myapplication.data.entities.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myapplication.data.entities.LanguageEntity
import com.example.myapplication.data.entities.WordEntity

data class WordAndLanguageEntity(
    @Embedded val word: WordEntity,
    @Relation(
        parentColumn = "language_id_fk",
        entityColumn = "langId"
    )
    val language: LanguageEntity
) {
    companion object
}
