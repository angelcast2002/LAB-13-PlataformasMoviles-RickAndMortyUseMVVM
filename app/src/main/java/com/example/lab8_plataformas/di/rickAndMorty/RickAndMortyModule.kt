package com.example.lab8_plataformas.di.rickAndMorty

import android.content.Context
import androidx.room.Room
import com.example.lab8_plataformas.data.local.RickAndMortyDb
import com.example.lab8_plataformas.data.local.dao.CharacterDao
import com.example.lab8_plataformas.data.remote.RickAndMortyApi
import com.example.lab8_plataformas.data.repository.characterRepository.CharacterRepository
import com.example.lab8_plataformas.data.repository.characterRepository.CharacterRepositoryImpl
import com.example.lab8_plataformas.di.rickAndMorty.urlConstants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RickAndMortyModule {

    @Provides
    @Singleton
    fun provideRickAndMortyDb(
        @ApplicationContext context: Context
    ): RickAndMortyDb{
        return Room.databaseBuilder(
            context,
            RickAndMortyDb::class.java,
            "rickAndMortyDb"
        ).build()
    }

    @Provides
    @Singleton
    fun privideCharacterDao(
        database: RickAndMortyDb
    ): CharacterDao {
        return database.characterDao()
    }

    @Provides
    @Singleton
    fun provideHttpInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ) : OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRickAndMortyApi(
        client: OkHttpClient
    ): RickAndMortyApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(RickAndMortyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(
        dao: CharacterDao,
        api: RickAndMortyApi
    ): CharacterRepository {
        return CharacterRepositoryImpl(
            characterDao = dao,
            api= api
        )
    }

}