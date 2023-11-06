package com.example.comicslibrary.di

import com.example.comicslibrary.network.ApiService
import com.example.comicslibrary.network.MarvelAPI
import com.example.comicslibrary.repository.MarvelApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {
    @Provides
    fun provideApiRepo() = MarvelApiRepository(ApiService.api)
}