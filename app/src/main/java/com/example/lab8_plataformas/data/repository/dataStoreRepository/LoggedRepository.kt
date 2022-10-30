package com.example.lab8_plataformas.data.repository.dataStoreRepository

import com.example.lab8_plataformas.data.Resource

interface LoggedRepository {

    suspend fun checkIsLogged(key: String): Resource<String>

    suspend fun saveData(key: String, value:String): Resource<String>

    suspend fun checkUserAndPassword(user: String, password: String): Resource<String>
}