package com.example.myapplication.data

import com.example.myapplication.domain.entities.ServiceResponse
import com.example.myapplication.domain.entities.WordModel
import com.example.myapplication.domain.repositories.IWordService
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults

class WordService (val databaseManager: Migrator): IWordService {

    private val realm: Realm

    init {
        val cfg = Migrator().configureDatabase()

        realm = Realm.open(cfg)
    }



    init {
//        this.createInitData()
    }

    private fun createInitData () {
        realm.writeBlocking {
            copyToRealm(
                WordModel().apply {
                    language = null
                    mainWord = "Test"
                    translatedWord = "Smth"
                    wordDescription = null
                }
            )
        }

        realm.writeBlocking {
            copyToRealm(
                WordModel().apply {
                    language = null
                    mainWord = "Test2"
                    translatedWord = "Smth2"
                    wordDescription = null
                }
            )
        }
    }

    override fun create(item: WordModel): ServiceResponse<String> {
        val result = ServiceResponse<String>()

        try {

            realm.writeBlocking {
                copyToRealm(item)
            }

            result.data = "ok"
            result.success = true
            result.error = null

        } catch (exc: Exception) {
            result.error = exc.localizedMessage
            result.success = false
            result.data = null
        }

        return result
    }

    override fun read(): ServiceResponse<RealmResults<WordModel>> {
        val result = ServiceResponse<RealmResults<WordModel>>()

        try {

            val items: RealmResults<WordModel> = realm.query(WordModel::class).find()

            result.data = items
            result.success = true
            result.error = null

        } catch (exc: Exception) {
            result.error = exc.localizedMessage
            result.success = false
            result.data = null
        }

        return result
    }

    override fun update(item: WordModel): ServiceResponse<String> {
        val result = ServiceResponse<String>()

        return  result
    }

    override fun delete(item: WordModel): ServiceResponse<String> {
        val result = ServiceResponse<String>()

        try {

            val word = realm.query<WordModel>("_id == $0", item._id).find()

            if (word.isNullOrEmpty()) {
                throw Exception()
            }

            realm.writeBlocking {
                delete(word)
            }

            result.data = "Ok"
            result.success = true

        } catch (ex: Exception) {
            result.error = ex.localizedMessage
        }

        return  result
    }

    override fun search(request: String): ServiceResponse<RealmResults<WordModel>> {
        val result = ServiceResponse<RealmResults<WordModel>>()

        return result
    }
}