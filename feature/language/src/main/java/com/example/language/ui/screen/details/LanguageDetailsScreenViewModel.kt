package com.example.language.ui.screen.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LanguageDetailsScreenViewModel(
//    private val getLanguageWithWordsByIdUseCase: GetLanguageWithWordsByIdUseCase
): ViewModel() {

    private val _state = mutableStateOf(LanguageDetailsScreenState())
    var viewState: State<LanguageDetailsScreenState> = _state

    fun handleIntent(intent: LanguageDetailsScreenIntent) {
        when(intent) {
            is LanguageDetailsScreenIntent.DeleteLanguageAndWords -> TODO()
            is LanguageDetailsScreenIntent.GetLanguageAndWordsById -> TODO()
        }
    }

//    private fun getLanguageWithWordsById(id: Int) {
//        viewModelScope.launch {
//            _state.value = _state.value.copy(isLoading = true)
//            val res = getLanguageWithWordsByIdUseCase.execute(id)
//
//            res.onSuccess { data ->
//
//                data.collect {
//                    _state.value = _state.value.copy(
//                        languageAndWords = it,
//                        isLoading = false
//                    )
//                }
//
//            }
//
//            res.onFailure {
//                _state.value = _state.value.copy(
//                    isError = true,
//                    errorMessage = it.localizedMessage,
//                    isLoading = false
//                )
//            }
//        }
//    }
}