package com.example.game.screen.entertranslate.screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.WordModel
import com.example.domain.usecase.word.GetWordsByLangIdUseCase
import kotlinx.coroutines.launch

class EnterTranslateScreenViewModel (
    private val getWordsByLangIdUseCase: GetWordsByLangIdUseCase
): ViewModel() {

    private val _state = mutableStateOf(EnterTranslateScreenState())
    val state: State<EnterTranslateScreenState> = _state

    fun handleIntent(intent: EnterTranslateScreenIntent) {
        when(intent) {
            is EnterTranslateScreenIntent.GetWords -> getWords(intent.id)
            is EnterTranslateScreenIntent.CheckAnswer -> checkAnswer()
            is EnterTranslateScreenIntent.SwipeLanguage -> switchWord()
        }
    }

    fun onUserTranslate (value: String) {
        _state.value = _state.value.copy(
            userTranslate = value
        )
    }

    fun isCheckEnable(): Boolean {
        return _state.value.userTranslate.trim() != ""
    }

    private fun getWords(id: Int) {
        viewModelScope.launch {

            val res = getWordsByLangIdUseCase.invoke(id)

            res.onSuccess { res ->

                res.collect { data ->

                    _state.value = _state.value.copy(
                        words = data,
                        currentWord = data.random(),
                        wordsCount = data.count(),
                    )

                }

            }

            res.onFailure {
                _state.value = _state.value.copy(
                    isError = true,
                    errorMessage = it.localizedMessage ?: "Failed fetch words"
                )
            }

        }
    }

    private fun checkAnswer() {
        if (_state.value.userTranslate == _state.value.currentWord?.translatedWord) {

            val correctAnswerCount = _state.value.correctAnswerCount + 1

            _state.value = _state.value.copy(
                isCorrect = true,
                isShowAnswer = true,
                isNextEnable = true,
                correctAnswerCount = correctAnswerCount
            )
        } else {

            val uncorrectWords: MutableList<String> = _state.value.uncorrectWords
            val correctWords: MutableList<String> = _state.value.correctWords

            _state.value.currentWord?.let {
                uncorrectWords.add(_state.value.userTranslate)
                correctWords.add(it.translatedWord)
            }

            Log.d("words", uncorrectWords.toString())

            _state.value = _state.value.copy(
                isCorrect = false,
                isShowAnswer = true,
                isNextEnable = true,
                uncorrectWords = uncorrectWords,
                correctWords = correctWords
            )
        }
    }

    private fun switchWord() {

        val currentWords = _state.value.words
        val words: List<WordModel>

        if(currentWords.count() > 1) {

            words = currentWords.filter { el ->
                el != _state.value.currentWord
            }

            val currentWord = words.random()
            val currentPosition = _state.value.currentWordPosition + 1

            _state.value = _state.value.copy(
                words = words,
                currentWord = currentWord,
                currentWordPosition = currentPosition
            )

            clearFields()

        } else {

            words = emptyList()

            _state.value = _state.value.copy(
                words = words,
                isShowStatsScreen = true
            )
        }

    }

    private fun clearFields() {
        _state.value = _state.value.copy(
            isNextEnable = false,
            isCorrect = false,
            isShowAnswer = false,
            userTranslate = ""
        )
    }

}