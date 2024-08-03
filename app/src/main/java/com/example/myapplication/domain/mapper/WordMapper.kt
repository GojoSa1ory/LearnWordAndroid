package com.example.myapplication.domain.mapper

import com.example.myapplication.data.model.WordModel
import com.example.myapplication.domain.entities.WordEntity

class WordMapper {

    fun mapToModel(word: WordEntity): WordModel {
        return WordModel (
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