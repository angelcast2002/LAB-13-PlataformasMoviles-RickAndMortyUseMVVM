package com.example.lab8_plataformas.ui.character_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab8_plataformas.data.Resource
import com.example.lab8_plataformas.data.repository.characterRepository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<CharacterListUiState> = MutableStateFlow(CharacterListUiState.Default)
    val uiState: StateFlow<CharacterListUiState> = _uiState

    fun getCharacters(){
        viewModelScope.launch {
            _uiState.value = CharacterListUiState.Loading
            when(val characterResult = repository.getCharacters()){
                is Resource.Error -> {
                    _uiState.value = CharacterListUiState.Error(characterResult.message?: "")
                }
                is Resource.Succes -> {
                    _uiState.value = CharacterListUiState.Succes(characterResult.data?: listOf())
                }
            }
        }
    }

}