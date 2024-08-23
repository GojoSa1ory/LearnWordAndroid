package com.example.database.entity.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.example.database.entity.LanguageEntity
import com.example.database.entity.WordEntity


data class WordAndLanguages (
    @Embedded val word: WordEntity,
    @Relation(
        parentColumn = "lang_id_fk",
        entityColumn = "id"
    )
    val language: LanguageEntity
)