package com.example.myapplication.data.mapper

import com.example.myapplication.data.entities.LanguageEntity
import com.example.myapplication.data.entities.WordEntity
import com.example.myapplication.domain.models.LanguageModel

fun LanguageEntity.Companion.mapToModel (lang: LanguageEntity): LanguageModel  {
    return LanguageModel(
        languageName = lang.languageName,
        langId = lang.langId
    )
}

fun LanguageModel.Companion.mapToEntity(lang: LanguageModel, isForCreate: Boolean): LanguageEntity {
    return if(isForCreate) {
        LanguageEntity(
            languageName = lang.languageName
        )
    } else {
        LanguageEntity(
            langId = lang.langId,
            languageName = lang.languageName
        )
    }
}