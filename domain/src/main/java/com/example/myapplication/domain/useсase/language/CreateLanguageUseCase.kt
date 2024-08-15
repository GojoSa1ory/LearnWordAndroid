package com.example.myapplication.domain.useсase.language

import com.example.myapplication.domain.models.LanguageModel
import com.example.myapplication.domain.repositories.LanguageRepository

class CreateLanguageUseCase(
    private val rep: LanguageRepository
) {
    suspend fun execute(item: LanguageModel): Result<Boolean> {
        return rep.createLanguage(item)
    }
}