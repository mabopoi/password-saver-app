package com.example.passwordsaver.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCases: LoginUseCases,
    private val injectedDispatcher: CoroutineDispatcher
): ViewModel(), PinCallbacks {

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    override fun onPinChange(pin: String) {
        _state.value = _state.value.copy(pin = pin)
    }

    override fun onPinUnlockClick() {
       viewModelScope.launch(injectedDispatcher) {
           val rightPin = loginUseCases.getPinUseCase("PIN")
           if(rightPin == _state.value.pin) {
               // do navigation
           } else {
               _state.value.pinError = "Incorrect PIN entered"
           }
       }
    }

    fun updatePin(pin: String) {
        viewModelScope.launch(injectedDispatcher) {
            loginUseCases.putPinUseCase("PIN", pin)
        }
    }
}