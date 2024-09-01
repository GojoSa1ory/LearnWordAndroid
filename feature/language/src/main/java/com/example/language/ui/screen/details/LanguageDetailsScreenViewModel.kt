package com.example.language.ui.screen.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.LanguageModel
import com.example.domain.model.WordModel
import com.example.domain.usecase.language.DeleteLanguageUseCase
import com.example.domain.usecase.language.GetLanguageAndWordsByIdUseCase
import com.example.domain.usecase.word.DeleteWordUseCase
import kotlinx.coroutines.launch

class LanguageDetailsScreenViewModel(
    private val getLanguageWithWordsByIdUseCase: GetLanguageAndWordsByIdUseCase,
    private val deleteWordUseCase: DeleteWordUseCase,
    private val deleteLanguageUseCase: DeleteLanguageUseCase
): ViewModel() {

    private val _state = mutableStateOf(LanguageDetailsScreenState())
    var viewState: State<LanguageDetailsScreenState> = _state

    fun handleIntent(intent: LanguageDetailsScreenIntent) {
        when(intent) {
            is LanguageDetailsScreenIntent.DeleteLanguageAndWords -> deleteLanguage(intent.lang)
            is LanguageDetailsScreenIntent.GetLanguageAndWordsById -> getLanguageWithWordsById(id = intent.id)
            is LanguageDetailsScreenIntent.DeleteWord -> deleteWord(intent.word)
        }
    }

    private fun getLanguageWithWordsById(id: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val res = getLanguageWithWordsByIdUseCase.invoke(id)

            res.onSuccess { data ->
                _state.value = _state.value.copy(
                    languageAndWords = data,
                    isLoading = false
                )
            }

            res.onFailure {
                _state.value = _state.value.copy(
                    isError = true,
                    errorMessage = it.localizedMessage,
                    isLoading = false
                )
            }
        }
    }

    private fun deleteWord(word: WordModel) {
        viewModelScope.launch {
            val res = deleteWordUseCase.invoke(word)

            res.onFailure {
                _state.value = _state.value.copy(
                    isError = true,
                    errorMessage = it.localizedMessage
                )
            }
        }
    }

    private fun deleteLanguage(lang: LanguageModel) {
        viewModelScope.launch {
            val res = deleteLanguageUseCase.invoke(lang)

            res.onFailure {
                _state.value = _state.value.copy(
                    isError = true,
                    errorMessage = it.localizedMessage,
                    isLoading = false
                )
            }
        }
    }
}