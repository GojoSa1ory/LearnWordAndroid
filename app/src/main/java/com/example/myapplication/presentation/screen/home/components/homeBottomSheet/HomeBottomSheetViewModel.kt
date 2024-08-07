package com.example.myapplication.presentation.screen.home.components.homeBottomSheet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.models.WordModel
import com.example.myapplication.domain.useсase.wordusecase.CreateWordUseCase
import kotlinx.coroutines.launch

class HomeBottomSheetViewModel(
    private val createWordUseCase: CreateWordUseCase
): ViewModel() {

    var wordValue by mutableStateOf("")
        private set

    var translatedWordValue by mutableStateOf("")
        private set

    var descriptionWordValue by mutableStateOf("")
        private set

    var showError by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf("")

    fun onWordValueChange(value: String) {
        wordValue = value
    }

    fun onTranslatedWordValueChange(value: String) {
        translatedWordValue = value
    }

    fun onDescriptionWordValueChange(value: String) {
        descriptionWordValue = value
    }

    fun onShowErrorChange () {
        showError = !showError
    }

    fun handleIntent (intent: HomeBottomSheetIntent) {
        when(intent) {
            is HomeBottomSheetIntent.AddWord -> createWord()
        }
    }

    fun isRequiredFieldEmpty (): Boolean {
        return wordValue.trim() == "" || translatedWordValue.trim() == ""
               || wordValue.trim() == " " || translatedWordValue.trim() == " "
    }

    private fun createWord () {

        val item = WordModel(
            mainWord = wordValue,
            translatedWord = translatedWordValue,
            wordDescription = descriptionWordValue
        )

        viewModelScope.launch {
            val res = createWordUseCase.execute(item)

            res.onFailure {
                onShowErrorChange()
                errorMessage = res.exceptionOrNull()?.localizedMessage.toString()
            }
        }

    }
}