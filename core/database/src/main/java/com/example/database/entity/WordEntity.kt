package com.example.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Words")
data class WordEntity(
    @PrimaryKey val mainWord: String,
    @ColumnInfo(name = "translated_word") val translatedWord: String,
    @ColumnInfo(name = "description_word") val descriptionWord: String,
    @ColumnInfo(name = "lang_id_fk") val languageId: Int = 0
) {
    companion object
}
