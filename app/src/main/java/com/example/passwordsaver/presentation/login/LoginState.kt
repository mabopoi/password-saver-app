package com.example.passwordsaver.presentation.login

data class LoginState(
    var pin: String = "",
    var pinError: String? = null,
)
