package com.example.lab8_plataformas.ui.logIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab8_plataformas.data.Resource
import com.example.lab8_plataformas.data.repository.dataStoreRepository.LoggedRepository
import com.example.lab8_plataformas.ui.logIn.LogInConstants.Companion.KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val repository: LoggedRepository
) : ViewModel() {

    private val _logInStatus: MutableStateFlow<LogInUiStatus> = MutableStateFlow(LogInUiStatus.Default)
    val logInStatus: StateFlow<LogInUiStatus> = _logInStatus

    fun checkIsLogged(){
        viewModelScope.launch {
            _logInStatus.value = LogInUiStatus.Loading
            when(val logInResult = repository.checkIsLogged(KEY)) {
                is Resource.Error -> {
                    _logInStatus.value = LogInUiStatus.Error(logInResult.message?:"")
                }
                is Resource.Succes -> {
                    _logInStatus.value = LogInUiStatus.Succes
                }
            }
        }
    }

    private fun saveLoggedUser(value: String){
        viewModelScope.launch {
            when(val saveDataResult = repository.saveData(KEY, value)) {
                is Resource.Error -> {
                    _logInStatus.value = LogInUiStatus.Error(saveDataResult.message?:"")
                }
                is Resource.Succes -> {
                    _logInStatus.value = LogInUiStatus.Succes
                }
            }
        }
    }

    fun checkUserAndPassword(user: String, password: String){
        viewModelScope.launch{
            _logInStatus.value = LogInUiStatus.Loading
            when (val checkResult = repository.checkUserAndPassword(user,password)) {
                is Resource.Error -> {
                    _logInStatus.value = LogInUiStatus.Error(checkResult.message?:"")
                }
                is Resource.Succes -> {
                    saveLoggedUser(user)
                }
            }
        }
    }

    fun setDefault(){
        _logInStatus.value = LogInUiStatus.Default
    }
}