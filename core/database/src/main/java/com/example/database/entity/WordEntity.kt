package com.example.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Words", foreignKeys = [
    ForeignKey(
        entity = LanguageEntity::class,
        parentColumns = ["id"],
        childColumns = ["lang_id_fk"],
        onDelete = ForeignKey.CASCADE
    )
])
data class WordEntity(
    @PrimaryKey val mainWord: String,
    @ColumnInfo(name = "translated_word") val translatedWord: String,
    @ColumnInfo(name = "description_word") val descriptionWord: String,
    @ColumnInfo(name = "lang_id_fk") val languageId: Int = 0
) {
    companion object
}
