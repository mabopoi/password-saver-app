package com.example.passwordsaver.domain.use_cases

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import javax.inject.Inject

class PutPinUseCase @Inject constructor(
    val dataStore: DataStore<Preferences>
) {
}