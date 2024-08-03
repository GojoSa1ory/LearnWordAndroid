package com.example.myapplication.domain.repositories

import com.example.myapplication.data.model.ServiceResponse

import com.example.myapplication.domain.entities.WordEntity

interface WordRepository {
    fun create(item: WordEntity): ServiceResponse<String>
    fun read(): ServiceResponse<List<WordEntity>>
    fun update(item: WordEntity): ServiceResponse<String>
    fun delete(item: WordEntity): ServiceResponse<String>
    fun search(query: String): ServiceResponse<List<WordEntity>>
}