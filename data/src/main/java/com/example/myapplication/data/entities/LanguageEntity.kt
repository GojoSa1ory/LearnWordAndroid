package com.example.myapplication.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Languages")
data class LanguageEntity (
    @PrimaryKey(autoGenerate = true) val langId: Int = 0,
    @ColumnInfo("language_name") val languageName: String,
) {
    companion object
}