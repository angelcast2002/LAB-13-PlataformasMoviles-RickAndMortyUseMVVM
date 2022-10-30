package com.example.lab8_plataformas.di.dataStore

import android.content.Context
import com.example.lab8_plataformas.data.repository.dataStoreRepository.LoggedRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Singleton
    @Provides
    fun provideDataStoreRepository(@ApplicationContext context: Context)
    = LoggedRepositoryImpl(context)
}