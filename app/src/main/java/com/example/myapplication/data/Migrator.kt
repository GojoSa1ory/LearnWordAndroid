package com.example.myapplication.data

import com.example.myapplication.data.model.LanguageModel
import com.example.myapplication.data.model.WordModel
import io.realm.kotlin.RealmConfiguration

class Migrator {
    fun configureDatabase(): RealmConfiguration {
        return  RealmConfiguration.create(
            schema = setOf(
                WordModel::class,
                LanguageModel::class
            )
        )
    }
}