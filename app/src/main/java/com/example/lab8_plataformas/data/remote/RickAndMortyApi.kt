package com.example.lab8_plataformas.data.remote

import com.example.lab8_plataformas.data.remote.dto.ResponseDto
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("/api/character")
    suspend fun getCharacters(): ResponseDto

}