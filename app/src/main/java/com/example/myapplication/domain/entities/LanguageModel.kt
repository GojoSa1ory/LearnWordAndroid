package com.example.myapplication.domain.entities

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class LanguageModel: RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var languageName: String = ""
    var words: RealmList<WordModel> = realmListOf()
}