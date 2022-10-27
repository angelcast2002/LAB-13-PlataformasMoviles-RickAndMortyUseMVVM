package com.example.lab8_plataformas.data.local

import androidx.room.Database
import com.example.lab8_plataformas.data.local.dao.CharacterDao

@Database(
    entities = [Character::class],
    version = 1

)
abstract class RickAndMortyDb {
    abstract fun characterDao(): CharacterDao
}