package com.example.comicslibrary.di

import android.content.Context
import androidx.room.Room
import com.example.comicslibrary.network.ApiService
import com.example.comicslibrary.repository.MarvelApiRepository
import com.example.comicslibrary.room.CharacterDao
import com.example.comicslibrary.room.CollectionDatabaseRepo
import com.example.comicslibrary.room.CollectionDatabaseRepoImpl
import com.example.comicslibrary.room.ComicsDatabase
import com.example.comicslibrary.room.Constants.DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {
    @Provides
    fun provideApiRepo() = MarvelApiRepository(ApiService.api)

    @Provides
    fun provideCollectionDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ComicsDatabase::class.java,DB)
            .build()

    @Provides
    fun provideCharacterDao(collectionDb : ComicsDatabase) = collectionDb.characterDao()

    @Provides
    fun provideDbRepoImpl(characterDao: CharacterDao) : CollectionDatabaseRepo =
        CollectionDatabaseRepoImpl(characterDao)
}