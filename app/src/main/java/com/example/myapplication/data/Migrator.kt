package com.example.myapplication.data

import com.example.myapplication.domain.entities.LanguageModel
import com.example.myapplication.domain.entities.WordModel
import io.realm.kotlin.RealmConfiguration

class Migrator {
//    var realm: Realm
//
//    init {
//        realm = Realm.open(configureDatabase())
//    }

    fun configureDatabase(): RealmConfiguration {
        return  RealmConfiguration.create(
            schema = setOf(
                WordModel::class,
                LanguageModel::class
            )
        )
    }
}