package com.myhg.mynewspaginationjetpackcompose.di

import com.myhg.mynewspaginationjetpackcompose.Constants.BASE_URL
import com.myhg.mynewspaginationjetpackcompose.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class DataModule {
    @Provides
    fun provideApiService(): ApiService {

        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)

    }
}