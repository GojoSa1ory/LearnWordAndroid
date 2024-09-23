package com.example.language.ui.screen.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.LanguageModel
import com.example.domain.model.WordModel
import com.example.domain.usecase.language.DeleteLanguageUseCase
import com.example.domain.usecase.language.GetLanguageAndWordsByIdUseCase
import com.example.domain.usecase.language.UpdateLanguageUseCase
import com.example.domain.usecase.word.DeleteWordUseCase
import kotlinx.coroutines.launch

class LanguageDetailsScreenViewModel(
    private val getLanguageWithWordsByIdUseCase: GetLanguageAndWordsByIdUseCase,
    private val deleteWordUseCase: DeleteWordUseCase,
    private val deleteLanguageUseCase: DeleteLanguageUseCase,
    private val updateLanguageUseCase: UpdateLanguageUseCase
): ViewModel() {

    private val _state = mutableStateOf(LanguageDetailsScreenState())
    var viewState: State<LanguageDetailsScreenState> = _state

    fun handleIntent(intent: LanguageDetailsScreenIntent) {
        when(intent) {
            is LanguageDetailsScreenIntent.DeleteLanguageAndWords -> deleteLanguage(intent.lang)
            is LanguageDetailsScreenIntent.GetLanguageAndWordsById -> getLanguageWithWordsById(id = intent.id)
            is LanguageDetailsScreenIntent.DeleteWord -> deleteWord(intent.word)
            is LanguageDetailsScreenIntent.EnableEditMode -> enableEditMode()
            is LanguageDetailsScreenIntent.UpdateModule -> updateModule()
        }
    }

    fun setNewTitle(title: String) {
        _state.value = _state.value.copy(
            newTitle = title
        )
    }

    private fun getLanguageWithWordsById(id: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val res = getLanguageWithWordsByIdUseCase.invoke(id)

            res.onSuccess { data ->

                data.collect {
                    _state.value = _state.value.copy(
                        languageAndWords = it,
                        isLoading = false
                    )
                }

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

    private fun enableEditMode () {

        val isEditMode = _state.value.isEditMode

        _state.value = _state.value.copy(
            isEditMode = !isEditMode
        )
    }

    private fun updateModule() {
        viewModelScope.launch {
            _state.value.languageAndWords?.language?.let { lang ->

                val newLang = LanguageModel(
                    id = lang.id,
                    languageName = _state.value.newTitle
                )

                updateLanguageUseCase.invoke(newLang)
            }
        }
    }
}