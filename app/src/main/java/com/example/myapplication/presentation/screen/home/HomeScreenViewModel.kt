package com.example.myapplication.presentation.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.models.WordModel
import com.example.myapplication.domain.useсase.wordusecase.DeleteWordUseCase
import com.example.myapplication.domain.useсase.wordusecase.GetWordsUseCase
import com.example.myapplication.domain.useсase.wordusecase.SearchWordUseCase
import kotlinx.coroutines.launch


class HomeScreenViewModel(
    private val getWordsUseCase: GetWordsUseCase,
    private val deleteWordsUseCase: DeleteWordUseCase,
    private val searchWordUseCase: SearchWordUseCase
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

        viewModelScope.launch {
            val res = getWordsUseCase.execute()

            res.onSuccess { data ->
                data.collect { words ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        words = words
                    )
                }
            }

            res.onFailure { data ->
                _state.value = _state.value.copy(
                    isLoading = false,
                    words = emptyList(),
                    error = data.message
                )
            }
        }

    }

    private fun deleteWord (word: WordModel) {
//        val res = deleteWordsUseCase.execute(word)
//
//        if (!res.success) {
//            _state.value = _state.value.copy(error = res.error)
//        }
    }

    private fun search (request: String) {
        viewModelScope.launch {
            val res = searchWordUseCase.execute(request)

            res.onSuccess { data ->
                data.collect {word ->
                    _state.value = _state.value.copy(
                        words = word
                    )
                }
            }

            res.onFailure { data ->
                _state.value = _state.value.copy(
                    words = emptyList()
                )
            }
        }
    }
}