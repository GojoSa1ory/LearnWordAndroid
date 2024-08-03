package com.example.myapplication.presentation.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.interfaces.IWordService
import com.example.myapplication.domain.entities.WordEntity
import com.example.myapplication.domain.useCase.WordUseCase
import com.example.myapplication.domain.useCase.wordUseCase.DeleteWordUseCase
import com.example.myapplication.domain.useCase.wordUseCase.GetWordsUseCase


class HomeScreenViewModel(
    private val getWordsUseCase: GetWordsUseCase,
    private val deleteWordsUseCase: DeleteWordUseCase
) : ViewModel() {

    private val _state = mutableStateOf(HomeScreenState())
    val viewState: State<HomeScreenState> = _state

    var request by mutableStateOf("")
        private set

    fun updateRequest(input: String) {
        request = input
    }

    fun handleIntent (intent: HomeScreenIntent) {
        when (intent) {
            is HomeScreenIntent.LoadWords -> loadWords()
            is HomeScreenIntent.DeleteWords -> deleteWord(intent.word)
            is HomeScreenIntent.Search -> search(intent.request)
        }
    }

    private fun loadWords () {
        _state.value = _state.value.copy(isLoading = true)

        val res = getWordsUseCase.execute()

        if (res.success) {

            res.data?.let { nonNullableList ->
                _state.value = _state.value.copy(isLoading = false, words = nonNullableList)
            }

        } else {
            _state.value = _state.value.copy(isLoading = false, error = res.error)
        }

    }

    private fun deleteWord (word: WordEntity) {
        val res = deleteWordsUseCase.execute(word)

        if (!res.success) {
            _state.value = _state.value.copy(error = res.error)
        }
    }

    private fun search (request: String) {

    }
}