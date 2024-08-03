package com.example.myapplication.domain.entities

import com.example.myapplication.data.model.LanguageModel
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

data class WordEntity (
    val _id: ObjectId = BsonObjectId(),
    val mainWord: String = "",
    val translatedWord: String = "",
    val wordDescription: String? = "",

    //MARK: Relationships
    val language: LanguageModel? = null
)