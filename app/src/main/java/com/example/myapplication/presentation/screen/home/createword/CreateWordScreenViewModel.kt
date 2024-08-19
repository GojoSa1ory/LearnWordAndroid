package com.example.myapplication.presentation.screen.home.createword

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.models.WordModel
import com.example.myapplication.domain.useсase.language.GetLanguagesUseCase
import com.example.myapplication.domain.useсase.word.CreateWordUseCase
import kotlinx.coroutines.launch

class CreateWordScreenViewModel(
    private val getLanguagesUseCase: GetLanguagesUseCase,
    private val createWordUseCase: CreateWordUseCase
): ViewModel() {

    private val _state = mutableStateOf(CreateWordScreenState())
    var state: State<CreateWordScreenState> = _state

    fun handelIntent(intent: CreateWordScreenIntent) {
        when(intent) {
            CreateWordScreenIntent.CreateWord -> createWord()
            CreateWordScreenIntent.GetLanguages -> getLanguages()
        }
    }

    private fun createWord() {

        val word = WordModel(
            mainWord = _state.value.mainWord.trim(),
            translatedWord = _state.value.translatedWord.trim(),
            wordDescription = _state.value.descriptionWord.trim(),
            languageId = _state.value.languageId
        )

        viewModelScope.launch {
            val res = createWordUseCase.execute(word)

            res.onFailure {
                _state.value = _state.value.copy(
                    isError = true,
                    errorMessage = it.localizedMessage?.toString() ?: "Failed to create word"
                )
            }
        }
    }

    private fun getLanguages() {
        viewModelScope.launch {
            val res = getLanguagesUseCase.execute()

            res.onSuccess {
                it.collect { data ->
                    _state.value = _state.value.copy(
                        languages = data
                    )
                }
            }

            res.onFailure {
                _state.value = _state.value.copy(
                    isError = true,
                    errorMessage = it.localizedMessage?.toString() ?: "Failed to get languages"
                )
            }

        }
    }

    fun checkRequiredFields(): Boolean {
        return _state.value.mainWord.trim().equals("") || _state.value.translatedWord.trim().equals("") || _state.value.languageId == 0
    }

    fun handleChangeMainWord(word: String) {
        _state.value = _state.value.copy(
            mainWord = word
        )
    }

    fun handleChangeTranslatedWord(word: String) {
        _state.value = _state.value.copy(
            translatedWord = word
        )
    }

    fun handleChangeWordDescription(desc: String) {
        _state.value = _state.value.copy(
            descriptionWord = desc
        )
    }

    fun handleChangeLanguageId(id: Int) {
        _state.value = _state.value.copy(
            languageId = id
        )
    }

    fun clearFields() {
        _state.value = _state.value.copy(
            mainWord = "",
            translatedWord = "",
            descriptionWord = "",
            languageId = 0
        )
    }

}