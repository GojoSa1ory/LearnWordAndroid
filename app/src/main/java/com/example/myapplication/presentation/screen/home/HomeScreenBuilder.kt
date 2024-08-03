package com.example.myapplication.presentation.screen.home

import androidx.compose.runtime.Composable
import com.example.myapplication.data.Migrator
import com.example.myapplication.data.service.WordService
import com.example.myapplication.data.repositories.WordRepositoryImpl
import com.example.myapplication.domain.useCase.WordUseCase
import com.example.myapplication.domain.useCase.wordUseCase.DeleteWordUseCase
import com.example.myapplication.domain.useCase.wordUseCase.GetWordsUseCase

class HomeScreenBuilder {
    @Composable
    fun buildScreen () {

        val service = WordService(Migrator())
        val wordRepository = WordRepositoryImpl(service)
        val getWordsUseCase = GetWordsUseCase(wordRepository)
        val deleteWordsUseCase = DeleteWordUseCase(wordRepository)
        val viewModel = HomeScreenViewModel(
            getWordsUseCase = getWordsUseCase,
            deleteWordsUseCase = deleteWordsUseCase
        )

        return HomeScreen(viewModel = viewModel)
    }
}