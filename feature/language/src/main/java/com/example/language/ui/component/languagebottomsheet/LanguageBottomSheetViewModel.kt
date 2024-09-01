package com.example.language.ui.component.languagebottomsheet

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.LanguageModel
import com.example.domain.usecase.language.CreateLanguageUseCase

import kotlinx.coroutines.launch

class LanguageBottomSheetViewModel(
    private val createLanguageUseCase: CreateLanguageUseCase
): ViewModel() {

    private val _state = mutableStateOf(LanguageBottomSheetState())
    var state: State<LanguageBottomSheetState> = _state


    fun handleIntent(intent: LanguageBottomSheetIntent) {
        when(intent) {
            LanguageBottomSheetIntent.CreateLanguage -> createLanguage()
        }
    }

    private fun createLanguage() {
        viewModelScope.launch {

            val langModel = LanguageModel(
                languageName = _state.value.languageName
            )

            val res = createLanguageUseCase.invoke(langModel)

            res.onFailure {

                _state.value = _state.value.copy(
                    isError = true,
                    error = it.localizedMessage ?: "Error to create language"
                )
            }

        }
    }

    fun handleLanguageNameChange(name: String) {

        Log.d("langName:", name)
        _state.value = _state.value.copy(
            languageName = name
        )

        Log.d("langName:", _state.value.languageName)
    }

    fun isRequiredFieldEmpty (): Boolean {
        return _state.value.languageName.equals("") || _state.value.languageName.trim().equals("")
    }

    fun clearField () {
        _state.value = _state.value.copy(
            languageName = ""
        )
    }

}