package com.example.passwordsaver.presentation.login

import com.example.passwordsaver.domain.use_cases.GetPinUseCase
import com.example.passwordsaver.domain.use_cases.PutPinUseCase

data class LoginUseCases(
    val getPinUseCase: GetPinUseCase,
    val putPinUseCase: PutPinUseCase
)
