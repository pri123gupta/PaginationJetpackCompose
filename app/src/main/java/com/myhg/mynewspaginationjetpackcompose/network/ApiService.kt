package com.myhg.mynewspaginationjetpackcompose.network

import com.myhg.mynewspaginationjetpackcompose.model.NewsApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //
//    https://api.instantwebtools.net/v1/passenger?page=0&size=1
    @GET("v1/passenger")
    suspend fun getNewsResponse(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 1

    ): Response<NewsApiResponse>
}