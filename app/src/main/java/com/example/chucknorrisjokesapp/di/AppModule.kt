package com.example.chucknorrisjokesapp.di

import com.example.chucknorrisjokesapp.data.ChuckNorrisRepository
import com.example.chucknorrisjokesapp.data.remote.ChuckNorrisRepositoryImp
import com.example.chucknorrisjokesapp.data.remote.ChuckNorrisService
import com.example.chucknorrisjokesapp.data.remote.dto.ChuckNorrisServiceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient{
        return HttpClient(Android){
            install(ContentNegotiation){
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
            install(Logging){
                level = LogLevel.ALL
            }
        }
    }

    @Provides
    @Singleton
    fun providesService(client: HttpClient): ChuckNorrisService {
        return ChuckNorrisServiceImp(client)
    }

    @Provides
    @Singleton
    fun providesRepository(service: ChuckNorrisService): ChuckNorrisRepository{
        return ChuckNorrisRepositoryImp(service)
    }
}