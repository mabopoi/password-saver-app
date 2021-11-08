package com.example.passwordsaver.presentation.login

interface PinCallbacks {
    fun onPinChange(pin: String)
    fun onPinUnlockClick()
}