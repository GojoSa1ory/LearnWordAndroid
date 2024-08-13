package com.example.myapplication.data.entities.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myapplication.data.entities.LanguageEntity
import com.example.myapplication.data.entities.WordEntity


data class LanguageAndWordsEntity(
    @Embedded val language: LanguageEntity,
    @Relation(
        parentColumn = "langId",
        entityColumn = "language_id_fk"
    )
    val words: List<WordEntity>
) {
    companion object
}