package com.example.game.screen.choosemodule.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.language.GetLanguageUseCase
import kotlinx.coroutines.launch

class ChooseScreenViewModel(
    private val getLanguageUseCase: GetLanguageUseCase
): ViewModel() {
    private val _state = mutableStateOf(ChooseScreenState())
    var state: State<ChooseScreenState> = _state

    fun handleIntent(intent: ChooseScreenIntent) {
        when(intent) {
            ChooseScreenIntent.GetLanguages -> getLanguages()
        }
    }

    private fun getLanguages() {

        viewModelScope.launch {

            val res = getLanguageUseCase.invoke()

            res.onSuccess { res ->

                res.collect { data ->

                    _state.value = _state.value.copy(
                        languages = data
                    )

                }

                _state.value = _state.value.copy(
                    loading = false
                )

            }

            res.onFailure {
                _state.value = _state.value.copy(
                    loading = false,
                    isError = true,
                    errorMessage = it.localizedMessage ?: "Error while fetch"
                )
            }

        }

    }
}