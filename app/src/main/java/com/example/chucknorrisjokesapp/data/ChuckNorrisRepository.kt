package com.example.chucknorrisjokesapp.data

import com.example.chucknorrisjokesapp.data.remote.dto.ChuckNorrisResponse
import com.example.chucknorrisjokesapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ChuckNorrisRepository {

    suspend fun getRandomJoke(category: String? = null): Resource<ChuckNorrisResponse>

    suspend fun getCategories(): Flow<Resource<List<String>>>
}