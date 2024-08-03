package com.example.myapplication.domain.repositories

import com.example.myapplication.domain.entities.ServiceResponse
import com.example.myapplication.domain.entities.WordModel
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.RealmList

interface IWordService: IBaseCRUD<WordModel> {
    fun search (request: String): ServiceResponse<RealmResults<WordModel>>
}