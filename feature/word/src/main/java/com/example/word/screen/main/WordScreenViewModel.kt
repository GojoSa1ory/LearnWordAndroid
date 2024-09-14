package com.example.word.screen.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.WordModel
import com.example.domain.usecase.word.DeleteWordUseCase
import com.example.domain.usecase.word.GetWordWithLanguageUseCase
import com.example.domain.usecase.word.SearchWordUseCase

import kotlinx.coroutines.launch

class WordScreenViewModel(
    private val getWordWithLanguageUseCase: GetWordWithLanguageUseCase,
    private val deleteWordUseCase: DeleteWordUseCase,
    private val searchWordUseCase: SearchWordUseCase
): ViewModel() {

    private val _state = mutableStateOf(WordScreenState())
    var state: State<WordScreenState> = _state

    fun handleIntent (intent: WordScreenIntent) {
        when(intent) {
            is WordScreenIntent.DeleteWord -> deleteWord(intent.word)
            is WordScreenIntent.GetWords -> getWords()
            is WordScreenIntent.SearchWord -> searchWords(intent.req)
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

    private fun deleteWord(word: WordModel) {
        viewModelScope.launch {
            deleteWordUseCase.invoke(word)
        }
    }

    private fun searchWords(req: String) {
        viewModelScope.launch {
            val res = searchWordUseCase.invoke(req)

            res.onSuccess { res ->
                res.collect { data ->
                    _state.value = _state.value.copy(
                        words = data
                    )
                }
            }

            res.onFailure {
                _state.value = _state.value.copy(
                    isError = true,
                    errorMessage = it.localizedMessage ?: "Search failed"
                )
            }
        }
    }

    fun updateReq(req: String) {
        _state.value = _state.value.copy(searchReq = req)
    }
}