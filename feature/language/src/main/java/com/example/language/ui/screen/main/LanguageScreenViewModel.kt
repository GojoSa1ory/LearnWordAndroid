package com.example.language.ui.screen.main

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.language.GetLanguageUseCase

import kotlinx.coroutines.launch

class LanguageScreenViewModel(
    private val getLanguagesUseCase: GetLanguageUseCase,
//    private val searchLanguageUseCase: SearchLanguageUseCase
): ViewModel() {

    private val _state = mutableStateOf(LanguageScreenState())
    val viewState: State<LanguageScreenState> = _state

    var searchReq by mutableStateOf("")
        private set


    fun handleIntent(intent: LanguageScreenIntent) {
        when(intent) {
            is LanguageScreenIntent.GetLanguages -> getLanguages()
            is LanguageScreenIntent.SearchLanguage -> TODO()
        }
    }

    private fun getLanguages() {
        viewModelScope.launch {


            val response = getLanguagesUseCase.invoke()

            response.onSuccess { res ->
                res.collect { lang ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        languages = lang
                    )
                }
            }

            response.onFailure { res ->
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = res.localizedMessage
                )
            }
        }
    }

//    private fun searchLanguage(req: String) {
//        viewModelScope.launch {
//            val res = searchLanguageUseCase.execute(req)
//
//            res.onSuccess { data ->
//                data.collect {
//                    _state.value = _state.value.copy(languages = it)
//                }
//            }
//
//            res.onFailure { exception ->
//                _state.value = _state.value.copy(error = exception.localizedMessage)
//            }
//        }
//    }

    fun handleReqChange(req: String) {
        this.searchReq = req
    }
}
