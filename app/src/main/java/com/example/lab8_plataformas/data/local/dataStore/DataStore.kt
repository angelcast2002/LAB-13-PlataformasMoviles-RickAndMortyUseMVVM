package com.example.lab8_plataformas.data.local.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore


object DataStore {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "logIn")
}