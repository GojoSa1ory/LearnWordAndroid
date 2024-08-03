package com.example.myapplication.presentation.screen.home

import androidx.compose.runtime.Composable
import com.example.myapplication.data.Migrator
import com.example.myapplication.data.dataSource.service.WordService
import com.example.myapplication.data.repositories.WordRepositoryImpl
import com.example.myapplication.domain.useCase.WordUseCase

class HomeScreenBuilder {
    @Composable
    fun buildScreen () {
        val service = WordService(Migrator())
        val wordRepository = WordRepositoryImpl(service)
        val wordUseCase = WordUseCase(wordRepository)
        val viewModel = HomeScreenViewModel(wordUseCase)

        return HomeScreen(viewModel = viewModel)
    }
}