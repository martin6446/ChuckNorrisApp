package com.example.chucknorrisjokesapp.data.remote

import com.example.chucknorrisjokesapp.data.ChuckNorrisRepository
import com.example.chucknorrisjokesapp.data.remote.dto.ChuckNorrisResponse
import com.example.chucknorrisjokesapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChuckNorrisRepositoryImp @Inject constructor(
    private val service: ChuckNorrisService
) : ChuckNorrisRepository {
    override suspend fun getRandomJoke(category: String?): Resource<ChuckNorrisResponse> {
        return service.getRandomJoke(category)
    }

    override suspend fun getCategories(): Flow<Resource<List<String>>> {
        return service.getCategories()
    }
}