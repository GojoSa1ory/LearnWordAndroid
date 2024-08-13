package com.example.myapplication.data.mapper

import com.example.myapplication.data.entities.relationships.LanguageAndWordsEntity
import com.example.myapplication.data.entities.LanguageEntity
import com.example.myapplication.data.entities.WordEntity
import com.example.myapplication.domain.models.LanguageAndWordsModel
import com.example.myapplication.domain.models.LanguageModel
import com.example.myapplication.domain.models.WordModel

fun LanguageAndWordsEntity.Companion.mapToModel(languageAndWords: LanguageAndWordsEntity): LanguageAndWordsModel {

    val langModel = LanguageEntity.mapToModel(languageAndWords.language)

    val wordsModel = languageAndWords.words.map { word ->
        WordEntity.mapToModel(word)
    }

    return LanguageAndWordsModel(
        language = langModel,
        words = wordsModel
    )
}

fun LanguageAndWordsModel.Companion.mapToEntity(languageAndWords: LanguageAndWordsModel, isForCreate: Boolean): LanguageAndWordsEntity {

    val langEntity = LanguageModel.mapToEntity(languageAndWords.language, isForCreate = isForCreate)

    val wordEntity = languageAndWords.words.map { word ->
        WordModel.mapToEntity(word, isForCreate = isForCreate)
    }

    return LanguageAndWordsEntity(
        language = langEntity,
        words = wordEntity
    )
}
