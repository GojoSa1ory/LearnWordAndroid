package com.example.word.screen.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.word.DeleteWordUseCase
import com.example.domain.usecase.word.GetWordWithLanguageUseCase
import com.example.domain.usecase.word.SearchWordUseCase

import kotlinx.coroutines.launch

class WordScreenViewModel(
    private val getWordWithLanguageUseCase: GetWordWithLanguageUseCase,
//    private val deleteWordUseCase: DeleteWordUseCase,
//    private val searchWordUseCase: SearchWordUseCase
): ViewModel() {

    private val _state = mutableStateOf(WordScreenState())
    var state: State<WordScreenState> = _state

    fun handleIntent (intent: WordScreenIntent) {
        when(intent) {
            is WordScreenIntent.DeleteWord -> TODO()
            is WordScreenIntent.GetWords -> getWords()
            is WordScreenIntent.SearchWord -> TODO()
        }
    }

    private fun getWords() {
        viewModelScope.launch {
            val res = getWordWithLanguageUseCase.invoke()

            res.onSuccess { data ->
                data.collect {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        words = it
                    )
                }

            }

            res.onFailure {
                _state.value = _state.value.copy(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.localizedMessage ?: "Error while fetch words"
                )
            }

        }
    }

    fun updateReq(req: String) {
        _state.value = _state.value.copy(searchReq = req)
    }
}