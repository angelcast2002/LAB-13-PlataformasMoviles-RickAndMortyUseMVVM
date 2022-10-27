package com.example.lab8_plataformas.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lab8_plataformas.data.local.entity.Character

interface CharacterDao {

    @Query("SELECT * FROM character")
    fun getCharacters(): List<Character>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(characters: List<Character>)

}