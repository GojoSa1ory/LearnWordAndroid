package com.example.myapplication.presentation.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.dataSource.interfaces.IWordService
import com.example.myapplication.domain.entities.WordEntity
import com.example.myapplication.domain.useCase.WordUseCase


class HomeScreenViewModel(
    private val useCase: WordUseCase
) : ViewModel() {

    private val _state = mutableStateOf(HomeScreenState())
    val viewState: State<HomeScreenState> = _state

    fun handleIntent (intent: HomeScreenIntent) {
        when (intent) {
            is HomeScreenIntent.LoadWords -> loadWords()
            is HomeScreenIntent.DeleteWords -> deleteWord(intent.word)
            is HomeScreenIntent.Search -> search(intent.request)
        }
    }

    private fun loadWords () {
        _state.value = _state.value.copy(isLoading = true)

        val res = useCase.read()

        if (res.success) {

            res.data?.let { nonNullableList ->
                _state.value = _state.value.copy(isLoading = false, words = nonNullableList)
            }

        } else {
            _state.value = _state.value.copy(isLoading = false, error = res.error)
        }

    }

    private fun deleteWord (word: WordEntity) {

    }

    private fun search (request: String) {

    }
}