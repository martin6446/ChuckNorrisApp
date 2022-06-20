package com.example.chucknorrisjokesapp.ui.random_joke_item

sealed class RandomJokeEvent {
   data class GetNewJoke(val category: String?): RandomJokeEvent()
}