package com.example.myapplication.domain.entities

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class WordModel: RealmObject {
    @PrimaryKey var _id:ObjectId = ObjectId()
    var mainWord: String = "Test"
    var translatedWord: String = "Тест"
    var wordDescription: String? = "Описание"

    //MARK: Relationships
    var language: LanguageModel? = null
}