package com.example.myapplication.data.mapper

import com.example.myapplication.data.entities.WordEntity
import com.example.myapplication.domain.models.WordModel

fun WordModel.Companion.mapToEntity (word: WordModel, isForCreate: Boolean): WordEntity {
    return if (isForCreate) {
        WordEntity(
            mainWord = word.mainWord,
            translatedWord = word.translatedWord,
            wordDescription = word.wordDescription,
            languageId = word.languageId
        )
    } else {
        WordEntity(
            wordId = word.wordId,
            mainWord = word.mainWord,
            translatedWord = word.translatedWord,
            wordDescription = word.wordDescription,
            languageId = word.languageId
        )
    }
}

fun WordEntity.Companion.mapToModel (word: WordEntity): WordModel {
    return WordModel(
        wordId = word.wordId,
        mainWord = word.mainWord,
        translatedWord = word.translatedWord,
        wordDescription = word.wordDescription,
        languageId = word.languageId
    )
}