package com.example.word.screen.create

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.WordModel
import com.example.domain.usecase.language.GetLanguageUseCase
import com.example.domain.usecase.word.CreateWordUseCase
import kotlinx.coroutines.launch

class CreateScreenViewModel(
    private val createWordUseCase: CreateWordUseCase,
    private val getLanguageUseCase: GetLanguageUseCase
): ViewModel() {

    private val _state = mutableStateOf(CreateScreenState())
    val state: State<CreateScreenState> = _state

    fun handleIntent(intent: CreateScreenIntent) {
        when (intent) {
            CreateScreenIntent.CreateWord -> createWord()
            CreateScreenIntent.GetLanguages -> getLanguages()
        }
    }

    private fun createWord() {
        viewModelScope.launch {

            val word = WordModel(
                mainWord = state.value.mainWordValue,
                translatedWord = state.value.translatedWordValue,
                wordDescription = state.value.descriptionWord,
                languageId = state.value.languageId,
                languageName = ""
            )

            val res = createWordUseCase.invoke(word)

            res.onFailure {
                _state.value = _state.value.copy(
                    isError = true,
                    errorMessage = it.localizedMessage ?: "Error while try add word"
                )
            }

        }
    }

    private fun getLanguages() {

        viewModelScope.launch {

            val res = getLanguageUseCase.invoke()

            res.onSuccess { data ->

                data.collect {
                    _state.value = _state.value.copy(
                        languages = it
                    )
                }

            }

            res.onFailure {
                _state.value = _state.value.copy(
                    isError = true,
                    errorMessage = it.localizedMessage ?: "Error while fetch languages"
                )
            }

        }

    }

    fun handleMainWordChange(str: String) {
        _state.value = _state.value.copy(
            mainWordValue = str
        )
    }

    fun handleTranslatedWordChange(str: String) {
        _state.value = _state.value.copy(
            translatedWordValue = str
        )
    }

    fun handleDescriptionWordChange(str: String) {
        _state.value = _state.value.copy(
            descriptionWord = str
        )
    }

    fun handleLanguageIdChange(id: Int) {
        _state.value = _state.value.copy(
            languageId = id
        )
    }

    fun checkRequiredFields(): Boolean {
        return state.value.mainWordValue.trim() == "" ||
                state.value.translatedWordValue.trim() == "" ||
                state.value.languageId == 0
    }

}