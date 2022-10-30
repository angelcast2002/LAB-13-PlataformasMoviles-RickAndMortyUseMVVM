package com.example.lab8_plataformas.data.repository.dataStoreRepository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.lab8_plataformas.data.Resource
import com.example.lab8_plataformas.data.local.dataStore.DataStore.dataStore
import com.example.lab8_plataformas.ui.logIn.LogInConstants.Companion.PASSWORD
import com.example.lab8_plataformas.ui.logIn.LogInConstants.Companion.USER

import com.example.lab8_plataformas.R
import kotlinx.coroutines.flow.first

class LoggedRepositoryImpl(private val context: Context): LoggedRepository {
    override suspend fun checkIsLogged(key: String): Resource<String> {

        return try {
            val dataStoreKey = stringPreferencesKey(key)
            val preferences = context.dataStore.data.first()
            val value = preferences[dataStoreKey].toString()

            if (value == USER) {
                Resource.Succes(value)
            } else {
                Resource.Error(context.getString(R.string.text_noHaIniciadoSesion))
            }

        } catch (e: Exception) {
            Resource.Error(e.message?:context.getString(R.string.errorE_logIn))
        }

    }

    override suspend fun saveData(key: String, value: String): Resource<String> {
        return try {
            val dataStoreKey = stringPreferencesKey(key)
            context.dataStore.edit { logIn ->
                logIn[dataStoreKey] = value
            }
            Resource.Succes(value)
        } catch (e: Exception) {
            Resource.Error(e.message?:context.getString(R.string.errorE_logIn))
        }

    }

    override suspend fun checkUserAndPassword(user: String, password: String): Resource<String> {
        return if (user == USER && password == PASSWORD) {
            Resource.Succes("")
        } else {
            Resource.Error(context.getString(R.string.mensajeError_LogIn_userorpasswordIncorrect))
        }
    }
}