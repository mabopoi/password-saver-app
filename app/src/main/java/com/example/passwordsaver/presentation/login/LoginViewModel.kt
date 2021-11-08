package com.example.passwordsaver.presentation.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val loginUseCases: LoginUseCases
): ViewModel(), PinCallbacks {
    override fun onPinChange(pin: String) {
        TODO("Not yet implemented")
    }

    override fun onPinUnlockClick() {
        TODO("Not yet implemented")
    }
}