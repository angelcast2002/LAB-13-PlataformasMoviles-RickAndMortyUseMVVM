package com.example.lab8_plataformas.ui.character_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab8_plataformas.data.repository.CharacterRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<CharacterListUiState> = MutableStateFlow(CharacterListUiState.Default)
    val uiState: StateFlow<CharacterListUiState> = _uiState

    fun getCharacters(){
        viewModelScope.launch {
            val characters = repository.getCharacters()
        }
    }

}