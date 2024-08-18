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

    var mainWord by mutableStateOf("")
        private set

    var translatedWord by mutableStateOf("")
        private set

    var descriptionWord by mutableStateOf("")
        private set

    var languageId by mutableStateOf(0)
        private set

    fun handelIntent(intent: CreateWordScreenIntent) {
        when(intent) {
            CreateWordScreenIntent.CreateWord -> createWord()
            CreateWordScreenIntent.GetLanguages -> getLanguages()
        }
    }

    private fun createWord() {

        val word = WordModel(
            mainWord = mainWord.trim(),
            translatedWord = translatedWord.trim(),
            wordDescription = descriptionWord.trim(),
            languageId = languageId
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
        return mainWord.trim().equals("") || translatedWord.trim().equals("") || languageId == 0
    }

    fun handleChangeMainWord(word: String) {
        mainWord = word
    }

    fun handleChangeTranslatedWord(word: String) {
        translatedWord = word
    }

    fun handleChangeWordDescription(desc: String) {
        descriptionWord = desc
    }

    fun handleChangeLanguageId(id: Int) {
        languageId = id
    }

    fun clearFields() {
        mainWord = ""
        translatedWord = ""
        descriptionWord = ""
        languageId = 0
    }

}