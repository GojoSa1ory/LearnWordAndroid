package com.example.myapplication.data.repositories

import com.example.myapplication.data.interfaces.IWordService
import com.example.myapplication.data.model.ServiceResponse
import com.example.myapplication.domain.mapper.WordMapper
import com.example.myapplication.domain.entities.WordEntity
import com.example.myapplication.domain.repositories.WordRepository

class WordRepositoryImpl(private val service: IWordService): WordRepository {

    override fun create(item: WordEntity): ServiceResponse<String> {

        val word = WordMapper().mapToModel(item)
        val response = service.create(word)

        return if (response.success) {
            ServiceResponse(response.data ?: "Success", success = true)
        } else {
            ServiceResponse(error = response.error)
        }
    }

    override fun read(): ServiceResponse<List<WordEntity>> {
        val response = service.read()
        return if (response.success) {

            val words = response.data?.let {words ->
                words.map { word ->
                    WordMapper().mapToEntity(word)
                }
            }

            ServiceResponse(data = words, success = true)

        } else {
            ServiceResponse(error = response.error)
        }
    }

    override fun update(item: WordEntity): ServiceResponse<String> {
        TODO("Not yet implemented")
    }

    override fun delete(item: WordEntity): ServiceResponse<String> {
        TODO("Not yet implemented")
    }

    override fun search(query: String): ServiceResponse<List<WordEntity>> {
        val response = service.search(query)

        return if (response.success) {

            val words = response.data?.let { data ->
                data.map { word ->
                    WordMapper().mapToEntity(word)
                }
            }

            ServiceResponse(data = words, success = true)
        } else {
            ServiceResponse(error = response.error)
        }
    }


}