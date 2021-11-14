package com.example.passwordsaver.di

import com.example.passwordsaver.domain.use_cases.GetPinUseCase
import com.example.passwordsaver.domain.use_cases.PutPinUseCase
import com.example.passwordsaver.presentation.login.LoginUseCases
import com.example.passwordsaver.utils.DataStoreUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LoginModule {
    @Provides
    @Singleton
    fun provideLoginUseCases(dataStoreUtil: DataStoreUtil) = LoginUseCases(
        getPinUseCase = GetPinUseCase(dataStoreUtil),
        putPinUseCase = PutPinUseCase(dataStoreUtil)
    )
}