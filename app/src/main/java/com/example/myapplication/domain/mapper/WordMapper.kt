package com.example.myapplication.domain.mapper

import com.example.myapplication.data.model.WordEntity
import com.example.myapplication.domain.models.WordModel

class WordMapper {

    fun mapToModel(word: WordEntity): WordModel {
        return WordModel (
            _id = word._id,
            language = word.language,
            mainWord = word.mainWord,
            translatedWord = word.translatedWord,
            wordDescription = word.wordDescription
        )
    }

    fun mapToEntity(word: WordModel): WordEntity {
        return WordEntity(
            _id = word._id,
            wordDescription = word.wordDescription,
            translatedWord = word.translatedWord,
            mainWord = word.mainWord,
            language = word.language
        )
    }
}