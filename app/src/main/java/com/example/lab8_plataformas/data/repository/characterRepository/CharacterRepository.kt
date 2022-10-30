package com.example.lab8_plataformas.data.repository.characterRepository


import com.example.lab8_plataformas.data.Resource
import com.example.lab8_plataformas.data.local.entity.Character

interface CharacterRepository {

    suspend fun getCharacters(): Resource<List<Character>>

}