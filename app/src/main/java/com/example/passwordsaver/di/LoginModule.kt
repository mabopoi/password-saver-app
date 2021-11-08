package com.example.passwordsaver.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.passwordsaver.domain.use_cases.GetPinUseCase
import com.example.passwordsaver.domain.use_cases.PutPinUseCase
import com.example.passwordsaver.presentation.login.LoginUseCases
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
    fun provideLoginUseCases(dataStore: DataStore<Preferences>) = LoginUseCases(
        getPinUseCase = GetPinUseCase(dataStore),
        putPinUseCase = PutPinUseCase(dataStore)
    )
}