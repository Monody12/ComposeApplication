package com.example.composeapplication.paging3

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

object Repository {

    private const val PAGE_SIZE = 2

    private val gitHubService = GitHubService.create()

    fun getPagingData(): Flow<PagingData<Repo>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { RepoPagingSource(gitHubService) }
        ).flow
    }

}
