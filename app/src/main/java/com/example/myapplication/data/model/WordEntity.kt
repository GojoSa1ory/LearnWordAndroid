package com.example.myapplication.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Words")
data class WordEntity(
    @PrimaryKey(autoGenerate = true) val _id: Int,
    @ColumnInfo(name = "main_word") val mainWord: String,
    @ColumnInfo(name = "translated_word") val translatedWord: String,
    @ColumnInfo(name = "word_description") val wordDescription: String?
)
