package com.example.data.mapper

import com.example.database.entity.LanguageEntity
import com.example.database.entity.WordEntity
import com.example.database.entity.relationships.LanguageAndWords
import com.example.domain.model.LanguageAndWordsModel
import com.example.domain.model.LanguageModel
import com.example.domain.model.WordModel

fun LanguageModel.Companion.mapToEntity(lang: LanguageModel): LanguageEntity {
    return LanguageEntity(
        id = lang.id,
        languageName = lang.languageName
    )
}

fun LanguageEntity.Companion.mapToModel(lang: LanguageEntity): LanguageModel {
    return LanguageModel(
        id = lang.id,
        languageName = lang.languageName
    )
}

fun LanguageAndWordsModel.Companion.mapToEntity(item: LanguageAndWordsModel): LanguageAndWords {
    return LanguageAndWords(
        language = LanguageModel.mapToEntity(item.language),
        words = item.words.map {
            WordModel.mapToEntity(it)
        }
    )
}

fun LanguageAndWords.Companion.mapToModel(item: LanguageAndWords): LanguageAndWordsModel {
    return LanguageAndWordsModel(
        language = LanguageEntity.mapToModel(item.language),
        words = item.words.map {
            WordEntity.mapToModel(it, languageName = item.language.languageName)
        }
    )
}

