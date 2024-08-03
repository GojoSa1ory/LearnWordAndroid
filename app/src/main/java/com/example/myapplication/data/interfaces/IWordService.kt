package com.example.myapplication.data.interfaces

import com.example.myapplication.data.model.WordModel
import com.example.myapplication.data.model.ServiceResponse
import io.realm.kotlin.query.RealmResults

interface IWordService: IBaseCRUD<WordModel> {
    fun search (request: String): ServiceResponse<RealmResults<WordModel>>
}