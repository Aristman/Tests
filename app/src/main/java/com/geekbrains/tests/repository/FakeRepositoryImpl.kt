package com.geekbrains.tests.repository

import com.geekbrains.tests.model.SearchResponse
import retrofit2.Response

class FakeRepositoryImpl : RepositoryContract {
    override fun searchGithub(query: String, callback: RepositoryCallback) {
        callback.handleGitHubResponse(Response.success(SearchResponse(55, listOf())))
    }
}
