package com.example.lab8_plataformas.data.repository

import com.example.lab8_plataformas.data.Resource
import com.example.lab8_plataformas.data.local.dao.CharacterDao
import com.example.lab8_plataformas.data.local.entity.Character
import com.example.lab8_plataformas.data.remote.RickAndMortyApi
import com.example.lab8_plataformas.data.remote.dto.mapToEntity

class CharacterRepositoryImpl(
    val localDb: CharacterDao,
    val api: RickAndMortyApi
): CharacterRepository {

    override suspend fun getCharacters(): Resource<List<Character>> {
        val localCharacters = localDb.getCharacters()
        return try {
            if (localCharacters.isEmpty()) {
                val remoteCharacters =
                    api.getCharacters().results
                val mappedCharacters =
                    remoteCharacters.map { characterDto -> characterDto.mapToEntity() }
                localDb.insertAll(mappedCharacters)
                Resource.Succes(mappedCharacters)
            } else {
                Resource.Succes(localCharacters)
            }
        } catch (ex: Exception) {
            Resource.Error(ex.message ?: "")
        }
    }
}