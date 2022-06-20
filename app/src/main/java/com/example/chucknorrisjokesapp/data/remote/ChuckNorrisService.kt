package com.example.chucknorrisjokesapp.data.remote

import com.example.chucknorrisjokesapp.data.remote.dto.ChuckNorrisResponse
import com.example.chucknorrisjokesapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ChuckNorrisService {

    suspend fun getRandomJoke(category: String? = null): Resource<ChuckNorrisResponse>

    suspend fun getCategories(): Flow<Resource<List<String>>>
}