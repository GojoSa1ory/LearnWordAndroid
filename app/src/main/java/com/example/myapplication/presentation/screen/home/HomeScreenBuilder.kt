package com.example.myapplication.presentation.screen.home

import androidx.compose.runtime.Composable
import com.example.myapplication.data.Migrator
import com.example.myapplication.data.service.WordService
import com.example.myapplication.data.repositories.WordRepositoryImpl
import com.example.myapplication.domain.useCase.wordUseCase.DeleteWordUseCase
import com.example.myapplication.domain.useCase.wordUseCase.GetWordsUseCase
import com.example.myapplication.domain.useCase.wordUseCase.SearchWordUseCase

class HomeScreenBuilder {
    @Composable
    fun buildScreen () {

        val service = WordService(Migrator())
        val wordRepository = WordRepositoryImpl(service)
        val getWordsUseCase = GetWordsUseCase(wordRepository)
        val deleteWordsUseCase = DeleteWordUseCase(wordRepository)
        val searchWordUseCase = SearchWordUseCase(wordRepository)
        val viewModel = HomeScreenViewModel(
            getWordsUseCase = getWordsUseCase,
            deleteWordsUseCase = deleteWordsUseCase,
            searchWordUseCase = searchWordUseCase
        )

        return HomeScreen(viewModel = viewModel)
    }
}