package com.checkfer.randomusers.di

import com.checkfer.randomusers.data.api.ApiEndpoint
import com.checkfer.randomusers.data.repository.RandomUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideRandomUserRepository(apiEndpoint: ApiEndpoint) = RandomUserRepository(apiEndpoint)
}