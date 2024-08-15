package com.example.myapplication.presentation.screen.language

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.useсase.language.GetLanguagesUseCase
import com.example.myapplication.domain.useсase.language.GetLanguagesWithWordsUseCase
import com.example.myapplication.domain.useсase.language.SearchLanguageUseCase
import kotlinx.coroutines.launch

class LanguageScreenViewModel(
    private val getLanguagesUseCase: GetLanguagesUseCase,
    private val searchLanguageUseCase: SearchLanguageUseCase
): ViewModel() {

    private val _state = mutableStateOf(LanguageScreenState())
    val viewState: State<LanguageScreenState> = _state

    var searchReq by mutableStateOf("")
        private set


    fun handleIntent(intent: LanguageScreenIntent) {
        when(intent) {
            is LanguageScreenIntent.GetLanguages -> getLanguages()
            is LanguageScreenIntent.SearchLanguage -> searchLanguage(intent.req)
        }
    }

    private fun getLanguages() {
        viewModelScope.launch {


            val response = getLanguagesUseCase.execute()

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

    private fun searchLanguage(req: String) {
        viewModelScope.launch {
            val res = searchLanguageUseCase.execute(req)

            res.onSuccess { data ->
                data.collect {
                    _state.value = _state.value.copy(languages = it)
                }
            }

            res.onFailure { exception ->
                _state.value = _state.value.copy(error = exception.localizedMessage)
            }
        }
    }

    fun handleReqChange(req: String) {
        this.searchReq = req
    }
}
