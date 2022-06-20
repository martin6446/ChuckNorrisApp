package com.example.chucknorrisjokesapp.data.remote.dto

import com.example.chucknorrisjokesapp.data.remote.ChuckNorrisService
import com.example.chucknorrisjokesapp.data.remote.Routes
import com.example.chucknorrisjokesapp.utils.Resource
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChuckNorrisServiceImp @Inject constructor(
    private val client: HttpClient
) : ChuckNorrisService {
    override suspend fun getRandomJoke(category: String?): Resource<ChuckNorrisResponse> {
        return try {
            Resource.Success(
                client.get {
                    url(Routes.RANDOM_JOKE_URL)

                    category?.let {
                        parameter("category",it)
                    }
                }.body()
            )
        } catch (e: ClientRequestException) {
            Resource.Error("There was an error fetching the data")
        } catch (e: ServerResponseException) {
            Resource.Error("The server is down")
        } catch (e: Exception) {
            Resource.Error("There was an error: ${e.message}")
        }
    }

    override suspend fun getCategories(): Flow<Resource<List<String>>> {
        return flow {
            emit(Resource.Loading(true))
            var categories = listOf<String>()
            try {
                categories = client.get {
                    url(Routes.JOKE_CATEGORIES)
                }.body()

            } catch (e: ClientRequestException) {
                emit(Resource.Error("There was an error fetching the data"))
            } catch (e: ServerResponseException) {
                emit(Resource.Error("The server is down"))
            } catch (e: Exception) {
                emit(Resource.Error("There was an error: ${e.message}"))
            }

            emit(Resource.Success(categories))
            emit(Resource.Loading(false))
            return@flow
        }
    }
}