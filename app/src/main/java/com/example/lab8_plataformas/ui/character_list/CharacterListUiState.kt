package com.example.lab8_plataformas.ui.character_list

import com.example.lab8_plataformas.data.local.entity.Character
sealed interface CharacterListUiState {
    data class Succes(val characters: List<Character>): CharacterListUiState
    object Loading: CharacterListUiState
    object Default: CharacterListUiState
    data class Error(val message: String): CharacterListUiState
}