package com.example.lab8_plataformas.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lab8_plataformas.data.local.entity.Character

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character")
    suspend fun getCharacters(): List<Character>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<Character>)

}