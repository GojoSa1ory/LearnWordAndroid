package com.example.myapplication.data.service

import com.example.myapplication.data.Migrator
import com.example.myapplication.data.model.WordModel
import com.example.myapplication.data.model.ServiceResponse
import com.example.myapplication.data.model.errors.DatabaseErrors
import com.example.myapplication.data.interfaces.IWordService
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query

import io.realm.kotlin.query.RealmResults

class WordService (val databaseManager: Migrator): IWordService {

    private val realm: Realm

    init {
        val cfg = Migrator().configureDatabase()
        realm = Realm.open(cfg)
//        this.createInitData()
    }

    private fun createInitData () {
        realm.writeBlocking {
            copyToRealm(
                WordModel(
                    language = null,
                    mainWord = "Test",
                    translatedWord = "Тест",
                    wordDescription = null
                )
            )
        }

        realm.writeBlocking {
            copyToRealm(
                WordModel(
                    language = null,
                    mainWord = "Wood",
                    translatedWord = "Дерево",
                    wordDescription = "Описание"
                )
            )
        }
    }

    override fun create(item: WordModel): ServiceResponse<String> {
        val result = ServiceResponse<String>()

        try {

            if (realm.isClosed()) {
                throw DatabaseErrors.DatabaseIsInvalid
            }

            realm.writeBlocking {
                copyToRealm(item)
            }


            result.data = "ok"
            result.success = true
            result.error = null

        }
        catch (exc: DatabaseErrors) {
            when(exc) {
                is DatabaseErrors.DatabaseIsInvalid -> {
                    result.error = DatabaseErrors.DatabaseIsInvalid.message
                }
                else -> {
                    result.error = "Error"
                }
            }
        }
        catch (exc: Exception) {
            result.error = exc.localizedMessage
            result.success = false
            result.data = null
        }

        return result
    }

    override fun read(): ServiceResponse<RealmResults<WordModel>> {
        val result = ServiceResponse<RealmResults<WordModel>>()

        try {

            if (realm.isClosed()) {
                throw DatabaseErrors.DeleteFailed
            }

            val items: RealmResults<WordModel> = realm.query(WordModel::class).find()

            result.data = items
            result.success = true
            result.error = null

        } catch (exc: DatabaseErrors) {
            when (exc) {
                is DatabaseErrors.DatabaseIsInvalid -> {
                    result.data = null
                    result.error = DatabaseErrors.DatabaseIsInvalid.message
                }

                else -> {
                    result.error = "Error"
                }
            }
        }
        catch (exc: Exception) {
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
                throw DatabaseErrors.DeleteFailed
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

        try {
            if (realm.isClosed()) throw DatabaseErrors.DatabaseIsInvalid

            val items: RealmResults<WordModel> = realm.query<WordModel>(
                "mainWord CONTAINS[c] $0 OR translatedWord CONTAINS[c] $0",
                request
            ).find()

            result.data = items
            result.success = true
        } catch (exc: DatabaseErrors) {
            result.error = exc.message
            result.success = false
        } catch (exc: Exception) {
            result.error = exc.localizedMessage ?: "Unknown error"
            result.success = false
        }

        return result
    }

}