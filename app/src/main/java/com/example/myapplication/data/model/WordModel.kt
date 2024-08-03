package com.example.myapplication.data.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

open class WordModel(
    @PrimaryKey var _id: ObjectId = BsonObjectId(),
    var mainWord: String = "",
    var translatedWord: String = "",
    var wordDescription: String? = null,
    var language: LanguageModel? = null
): RealmObject {
    constructor() : this(BsonObjectId(), "", "", null, null)
}
