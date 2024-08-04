package com.example.myapplication.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Languages")
data class LanguageModel (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo("language_name") val language: String
)