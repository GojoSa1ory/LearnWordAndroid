package com.example.myapplication.presentation.screen.home.components.homeBottomSheet

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.models.LanguageModel
import com.example.myapplication.domain.models.WordModel
import com.example.myapplication.domain.useсase.language.GetLanguagesUseCase
import com.example.myapplication.domain.useсase.word.CreateWordUseCase
import kotlinx.coroutines.launch

class HomeBottomSheetViewModel(
    private val createWordUseCase: CreateWordUseCase,
    private val getLanguagesUseCase: GetLanguagesUseCase
): ViewModel() {

    private val _state = mutableStateOf(HomeBottomSheetState())
    val state: State<HomeBottomSheetState> = _state

    var wordValue by mutableStateOf("")
        private set

    var translatedWordValue by mutableStateOf("")
        private set

    var descriptionWordValue by mutableStateOf("")
        private set

    var selectedLanguage by mutableStateOf<LanguageModel?>(null)
        private set


    fun handleIntent (intent: HomeBottomSheetIntent) {
        when(intent) {
            is HomeBottomSheetIntent.AddWord -> createWord()
            is HomeBottomSheetIntent.GetLanguages -> getLanguages()
        }
    }

    private fun createWord () {

        val item = WordModel(
            mainWord = wordValue,
            translatedWord = translatedWordValue,
            wordDescription = descriptionWordValue,
            languageId = this.selectedLanguage?.langId ?: 0
        )

        viewModelScope.launch {
            val res = createWordUseCase.execute(item)

            res.onFailure {
                _state.value = _state.value.copy(
                    isError = true,
                    errorMessage = res.exceptionOrNull()
                        ?.localizedMessage.toString()
                )
            }
        }

    }

    private fun getLanguages() {
        viewModelScope.launch {
            val res = getLanguagesUseCase.execute()

            res.onSuccess { res ->
                res.collect { data ->
                    data.map {
                        _state.value = _state.value.copy(languages = data)
                    }
                }

            }

            res.onFailure {
                _state.value = _state.value.copy(
                    isError = true,
                    errorMessage = res.exceptionOrNull()
                        ?.localizedMessage.toString())
            }
        }
    }


    fun isRequiredFieldEmpty (): Boolean {
        return wordValue.trim() == "" || translatedWordValue.trim() == ""
                || wordValue.trim() == " " || translatedWordValue.trim() == " "
                || selectedLanguage == null
    }

    fun handleWordValueChange(value: String) {
        wordValue = value
    }

    fun handleTranslatedWordValueChange(value: String) {
        translatedWordValue = value
    }

    fun handleDescriptionWordValueChange(value: String) {
        descriptionWordValue = value
    }

    fun onSelectLang(lang: LanguageModel) {
        this.selectedLanguage = lang
    }

    fun clearFields() {
        wordValue = ""
        translatedWordValue = ""
        descriptionWordValue = ""
        selectedLanguage = null
    }
}