package com.example.myapplication.presentation.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.entities.WordModel
import com.example.myapplication.domain.repositories.IWordService



class HomeScreenViewModel(
    val service: IWordService
) : ViewModel() {

    private val _state = mutableStateOf(HomeScreenState())
    val viewState: State<HomeScreenState> = _state

    fun handleIntent (intent: HomeScreenIntent) {
        when (intent) {
            is HomeScreenIntent.LoadWords -> loadWords()
            is HomeScreenIntent.DeleteWords -> deleteWord(intent.word)
        }
    }

    private fun loadWords () {
        _state.value = HomeScreenState(isLoading = true)

        val res = service.read()

        if (res.success) {
            _state.value = HomeScreenState(words = res.data!!.toList())
        } else {
            _state.value = HomeScreenState(error = res.error)
        }

    }

    private fun deleteWord (word: WordModel) {

    }
}