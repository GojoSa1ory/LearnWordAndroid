package com.example.myapplication.data.mapper

import com.example.myapplication.data.entities.LanguageEntity
import com.example.myapplication.data.entities.relationships.WordAndLanguageEntity
import com.example.myapplication.data.entities.WordEntity
import com.example.myapplication.domain.models.LanguageModel
import com.example.myapplication.domain.models.WordAndLanguageModel
import com.example.myapplication.domain.models.WordModel

fun WordAndLanguageEntity.Companion.mapToModel (wordAndLanguageEntity: WordAndLanguageEntity): WordAndLanguageModel {

    val word = WordEntity.mapToModel(wordAndLanguageEntity.word)
    val lang = LanguageEntity.mapToModel(wordAndLanguageEntity.language)

    return WordAndLanguageModel(
        word = word,
        language = lang
    )
}

fun WordAndLanguageModel.Companion.mapToEntity (wordAndLanguageModel: WordAndLanguageModel, isForCreate: Boolean): WordAndLanguageEntity {

    val word = WordModel.mapToEntity(wordAndLanguageModel.word, isForCreate = isForCreate)
    val lang = LanguageModel.mapToEntity(wordAndLanguageModel.language)

    return WordAndLanguageEntity(
        word = word,
        language = lang
    )
}