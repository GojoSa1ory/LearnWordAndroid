package com.example.game.screen.chooserightvariant.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.WordModel
import com.example.domain.usecase.word.GetWordsByLangIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ChooseCorrectAnswerScreenViewModel(
    private val getWordsByLangIdUseCase: GetWordsByLangIdUseCase
): ViewModel() {

    private val _state = mutableStateOf(ChooseCorrectAnswerScreenState())
    var state: State<ChooseCorrectAnswerScreenState> = _state

    fun handleIntent(intent: ChooseCorrectAnswerScreenIntent) {
        when(intent) {
            is ChooseCorrectAnswerScreenIntent.CheckAnswer -> checkAnswer(answer = intent.word)
            is ChooseCorrectAnswerScreenIntent.FetchWords -> fetchWords(intent.id)
            is ChooseCorrectAnswerScreenIntent.SwitchAnswer -> switchWord()
        }
    }

    private fun fetchWords(id: Int) {

        viewModelScope.launch {

            val res = getWordsByLangIdUseCase.invoke(id)

            res.onSuccess { res ->

                res.collect {data ->

                    val currentWord = data.random()

                    val answers:MutableList<String> = mutableListOf(
                        currentWord.translatedWord,
                        data.random().translatedWord,
                        data.random().translatedWord,
                        data.random().translatedWord,
                    )

                    _state.value = _state.value.copy(
                        words = data,
                        currentWord = currentWord,
                        answers = answers,
                        wordsCount = data.count()
                    )

                }
            }

            res.onFailure {
                _state.value = _state.value.copy(
                    isError = true,
                    errMessage = it.localizedMessage ?: "Error while fetch words"
                )
            }
        }
    }

    private fun checkAnswer(answer: String) {
        _state.value.currentWord?.let { word ->

            if(word.translatedWord.trim().lowercase() == answer.trim().lowercase()) {
                _state.value = _state.value.copy(
                    isCorrect = true,
                    isNextEnable = true
                )
            } else {
                _state.value = _state.value.copy(
                    isCorrect = false,
                    isNextEnable = true
                )
            }
        }
    }

    private fun switchWord() {
        val currentWords = _state.value.words

        if (currentWords.size > 1) {
            val words = currentWords.filter { el -> el != _state.value.currentWord }
            val currentWord = words.random()
            val currentPosition = _state.value.currentWordPosition + 1
            val answers = listOf(
                currentWord.translatedWord,
                words.random().translatedWord,
                words.random().translatedWord,
                words.random().translatedWord
            )

            // Обновляем состояние с новым словом
            _state.value = _state.value.copy(
                words = words,
                currentWord = currentWord,
                currentWordPosition = currentPosition,
                answers = answers
            )

            // Очищаем поля после смены слова
            clearFields()

        } else {
            // Когда слов больше не осталось, устанавливаем состояние для отображения экрана статистики
            _state.value = _state.value.copy(
                isShowStatsScreen = true
            )

            // Очищаем слова, чтобы больше не было попыток переключить их
            _state.value = _state.value.copy(
                words = emptyList()
            )

            // Очищаем поля
            clearFields()
        }
    }



    private fun clearFields() {
        _state.value = _state.value.copy(
            isCorrect = false,
            isNextEnable = false
        )
    }
}