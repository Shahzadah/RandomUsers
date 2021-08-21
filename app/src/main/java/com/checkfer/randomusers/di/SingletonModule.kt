package com.checkfer.randomusers.di

import com.checkfer.randomusers.BuildConfig
import com.checkfer.randomusers.data.api.ServiceClient
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    @Provides
    fun provideServiceClient()= ServiceClient(BuildConfig.BASE_API_URL, Gson())

    @Provides
    fun provideApiEndpoint(serviceClient: ServiceClient) = serviceClient.getRestService()
}