package com.example.lab8_plataformas.ui.logIn

sealed interface LogInUiStatus{
    object Default: LogInUiStatus
    object Loading: LogInUiStatus
    object Succes: LogInUiStatus
    data class Error(val message: String): LogInUiStatus
}