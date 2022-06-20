package com.example.chucknorrisjokesapp.ui.random_joke_item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorrisjokesapp.data.ChuckNorrisRepository
import com.example.chucknorrisjokesapp.mapper.toModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChuckNorrisRandomJokeViewModel @Inject constructor(
    private val repository: ChuckNorrisRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var joke by mutableStateOf("")
    var category by mutableStateOf<String?>(null)

    init {
        category = savedStateHandle.get<String>("category")

        getRandomJoke(category)
    }

    private fun getRandomJoke(category: String?) {
        viewModelScope.launch {
            delay(500)
            val response = repository.getRandomJoke(category).data
            joke = response?.toModel()?.joke ?: ""
        }
    }

    fun resetCategory(category: String? = null){
        this.category = category
        joke = ""
        getRandomJoke(category)
    }

    fun onEvent(event: RandomJokeEvent) {
        when (event) {
            is RandomJokeEvent.GetNewJoke -> {
                joke = ""
                resetCategory(event.category)
            }
        }
    }
}