package com.example.myapplication.presentation.screen.language.component.languagebottomsheet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.models.LanguageModel
import com.example.myapplication.domain.useсase.language.CreateLanguageUseCase
import kotlinx.coroutines.launch

class LanguageBottomSheetViewModel(
    private val createLanguageUseCase: CreateLanguageUseCase
): ViewModel() {

    var languageName by mutableStateOf("")
        private set

    var errorMessage by mutableStateOf("")
        private set

    var isError by mutableStateOf(false)
        private set

    fun handleIntent(intent: LanguageBottomSheetIntent) {
        when(intent) {
            LanguageBottomSheetIntent.CreateLanguage -> createLanguage()
        }
    }

    private fun createLanguage() {
        viewModelScope.launch {

            val langModel = LanguageModel(
                languageName = languageName
            )

            val res = createLanguageUseCase.execute(langModel)

            res.onFailure {
                isError = true
                errorMessage = it.localizedMessage?.toString().toString()
            }

        }
    }

    fun handleLanguageNameChange(name: String) {
        this.languageName = name
    }

    fun isRequiredFieldEmpty (): Boolean {
        return languageName.equals("") || languageName.trim().equals("")
    }

    fun clearField () {
        languageName = ""
    }

}