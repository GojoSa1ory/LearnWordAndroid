package com.example.myapplication.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Words",
    foreignKeys = [
        ForeignKey(
            entity = LanguageEntity::class,
            parentColumns = ["langId"],
            childColumns = ["language_id_fk"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["language_id_fk"])]
)
data class WordEntity(
    @PrimaryKey @ColumnInfo(name = "main_word") val mainWord: String,
    @ColumnInfo(name = "translated_word") val translatedWord: String,
    @ColumnInfo(name = "word_description") val wordDescription: String?,
    @ColumnInfo(name = "language_id_fk") val languageId: Int
) {
    companion object
}
