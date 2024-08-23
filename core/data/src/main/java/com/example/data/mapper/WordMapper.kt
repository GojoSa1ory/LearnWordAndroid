package com.example.data.mapper

import com.example.database.entity.WordEntity

fun com.example.domain.model.WordModel.Companion.mapToEntity(word: com.example.domain.model.WordModel): WordEntity {
    return WordEntity(
        mainWord = word.mainWord,
        translatedWord = word.translatedWord,
        descriptionWord = word.wordDescription,
        languageId = word.languageId
    )
}

fun WordEntity.Companion.mapToModel(word: WordEntity, languageName: String): com.example.domain.model.WordModel {
    return com.example.domain.model.WordModel(
        mainWord = word.mainWord,
        translatedWord = word.translatedWord,
        wordDescription = word.descriptionWord,
        languageId = word.languageId,
        languageName = languageName
    )
}