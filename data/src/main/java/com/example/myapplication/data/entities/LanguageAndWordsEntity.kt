package com.example.myapplication.data.entities

import androidx.room.Embedded
import androidx.room.Relation


data class LanguageAndWordsEntity(
    @Embedded val language: LanguageEntity,
    @Relation(
        parentColumn = "language_id",
        entityColumn = "langId"
    )
    val words: List<WordEntity>
) {
    companion object
}