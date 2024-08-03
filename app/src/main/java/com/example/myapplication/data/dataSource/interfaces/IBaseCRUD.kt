package com.example.myapplication.data.dataSource.interfaces

import com.example.myapplication.data.model.ServiceResponse
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.BaseRealmObject

interface IBaseCRUD<T : BaseRealmObject> {
    fun create (item: T): ServiceResponse<String>
    fun read (): ServiceResponse<RealmResults<T>>
    fun update (item: T): ServiceResponse<String>
    fun delete (item: T): ServiceResponse<String>
}