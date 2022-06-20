package com.example.chucknorrisjokesapp.mapper

import com.example.chucknorrisjokesapp.data.remote.dto.ChuckNorrisResponse
import com.example.chucknorrisjokesapp.model.ChuckNorrisJokeModel

fun ChuckNorrisResponse.toModel(): ChuckNorrisJokeModel{
    return ChuckNorrisJokeModel(
        joke = joke
    )
}