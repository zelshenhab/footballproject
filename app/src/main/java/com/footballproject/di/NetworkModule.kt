package com.footballproject.di

import com.footballproject.core.Constants
import com.footballproject.data.remote.FootballApi
import com.footballproject.data.remote.interceptor.ApiKeyInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(): ApiKeyInterceptor = ApiKeyInterceptor()

    @Provides
    @Singleton
    fun provideHttpClient(
        apiKeyInterceptor: ApiKeyInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        httpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit =
        Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(gsonConverterFactory)
            .baseUrl(Constants.BASE_URL)
            .build()

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideApi(
        retrofit: Retrofit,
    ): FootballApi = retrofit.create(FootballApi::class.java)
}
