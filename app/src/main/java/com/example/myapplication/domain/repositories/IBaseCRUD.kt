package com.example.myapplication.domain.repositories

import com.example.myapplication.domain.entities.ServiceResponse
import com.example.myapplication.domain.entities.WordModel
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.BaseRealmObject
import io.realm.kotlin.types.RealmList

interface IBaseCRUD<T : BaseRealmObject> {
    fun create (item: T): ServiceResponse<String>
    fun read (): ServiceResponse<RealmResults<T>>
    fun update (item: T): ServiceResponse<String>
    fun delete (item: T): ServiceResponse<String>
}