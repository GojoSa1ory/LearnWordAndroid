package com.example.data.mapper

import com.example.database.entity.LanguageEntity
import com.example.domain.model.LanguageModel

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

