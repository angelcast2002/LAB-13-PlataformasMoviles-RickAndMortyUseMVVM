package com.example.lab8_plataformas.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lab8_plataformas.data.local.dao.CharacterDao
import com.example.lab8_plataformas.data.local.entity.Character

@Database(
    entities = [Character::class],
    version = 1

)
abstract class RickAndMortyDb: RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}