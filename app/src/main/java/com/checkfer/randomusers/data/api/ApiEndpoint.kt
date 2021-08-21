package com.checkfer.randomusers.data.api

import com.checkfer.randomusers.data.model.RandomUserResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiEndpoint {

    @GET("api/?results=10")
    suspend fun fetchRandomUsers(): Response<RandomUserResponse>
}