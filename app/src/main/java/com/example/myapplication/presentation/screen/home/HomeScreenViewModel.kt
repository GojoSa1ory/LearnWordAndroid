package com.example.myapplication.presentation.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.models.LanguageModel
import com.example.myapplication.domain.models.WordModel
import kotlinx.coroutines.launch


class HomeScreenViewModel(
    private val getWordsUseCase: com.example.myapplication.domain.useсase.word.GetWordsUseCase,
    private val deleteWordsUseCase: com.example.myapplication.domain.useсase.word.DeleteWordUseCase,
    private val searchWordUseCase: com.example.myapplication.domain.useсase.word.SearchWordUseCase
) : ViewModel() {

    private val _state = mutableStateOf(HomeScreenState())
    val viewState: State<HomeScreenState> = _state

    var request by mutableStateOf("")
        private set


    fun handleIntent(intent: HomeScreenIntent) {
        when (intent) {
            is HomeScreenIntent.LoadWords -> loadWords()
            is HomeScreenIntent.DeleteWords -> deleteWord(intent.word)
            is HomeScreenIntent.Search -> search(intent.request)
        }
    }

    private fun loadWords() {
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

    private fun deleteWord(word: WordModel) {
        viewModelScope.launch {
            val res = deleteWordsUseCase.execute(word)

            res.onFailure { error ->
                _state.value = _state.value.copy(error = error.localizedMessage)
            }
        }
    }

    private fun search(request: String) {
        viewModelScope.launch {
            val res = searchWordUseCase.execute(request)

            res.onSuccess { data ->
                data.collect { word ->
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

    fun updateRequest(value: String) {
        request = value
    }

}