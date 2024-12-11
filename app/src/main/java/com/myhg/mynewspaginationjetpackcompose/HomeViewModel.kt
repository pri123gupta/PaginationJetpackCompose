package com.myhg.mynewspaginationjetpackcompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.myhg.mynewspaginationjetpackcompose.model.Data
import com.myhg.mynewspaginationjetpackcompose.network.ApiService
import com.myhg.mynewspaginationjetpackcompose.paging.MyPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    val pager: Flow<PagingData<Data>> = Pager(
        config = PagingConfig(pageSize = 5, prefetchDistance = 3),
        pagingSourceFactory = { MyPagingSource(apiService = apiService) }
    ).flow.cachedIn(viewModelScope)
}