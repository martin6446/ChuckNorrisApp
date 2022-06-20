package com.example.chucknorrisjokesapp.utils

sealed class UiEvent() {
    object PopBackStack: UiEvent()
    data class Navigate(val route: String): UiEvent()
}