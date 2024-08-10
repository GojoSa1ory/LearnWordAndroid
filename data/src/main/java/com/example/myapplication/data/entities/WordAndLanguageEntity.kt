package com.example.myapplication.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class WordAndLanguageEntity(
    @Embedded val word: WordEntity,
    @Relation(
        parentColumn = "language_id",
        entityColumn = "langId"
    )
    val language: LanguageEntity
) {
    companion object
}
