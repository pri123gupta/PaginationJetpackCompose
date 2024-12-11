package com.myhg.mynewspaginationjetpackcompose.model

data class NewsApiResponse(
    val `data`: List<Data>,
    val totalPages: Int,
    val totalPassengers: Int
)