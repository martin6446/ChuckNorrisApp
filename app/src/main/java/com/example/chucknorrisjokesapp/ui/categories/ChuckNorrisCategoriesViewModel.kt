package com.example.chucknorrisjokesapp.ui.categories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorrisjokesapp.data.remote.ChuckNorrisService
import com.example.chucknorrisjokesapp.utils.Resource
import com.example.chucknorrisjokesapp.utils.Routes
import com.example.chucknorrisjokesapp.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChuckNorrisCategoriesViewModel @Inject constructor(
    private val service: ChuckNorrisService
) : ViewModel() {

    var categories by mutableStateOf<List<String>>(emptyList())

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            service.getCategories()
                .collect { result ->
                    when (result) {
                        is Resource.Error -> { }
                        is Resource.Loading -> { }
                        is Resource.Success -> categories = result.data ?: emptyList()
                    }
                }
        }
    }

    fun onEvent(event: CategoriesEvent){
        when(event){
            is CategoriesEvent.OnCategoryClick -> {
                sendEvent(UiEvent.Navigate(Routes.RANDOM_JOKE + "?category=${event.category}"))
            }
        }
    }

    private fun sendEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}