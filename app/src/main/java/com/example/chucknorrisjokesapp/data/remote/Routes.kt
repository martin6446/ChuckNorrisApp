package com.example.chucknorrisjokesapp.data.remote

object Routes {
    private const val BASIC_URL = "https://api.chucknorris.io/jokes"
    const val RANDOM_JOKE_URL = "$BASIC_URL/random"
    const val JOKE_CATEGORIES = "$BASIC_URL/categories"
}