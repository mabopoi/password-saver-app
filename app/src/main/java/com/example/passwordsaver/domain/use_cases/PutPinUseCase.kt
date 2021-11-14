package com.example.passwordsaver.domain.use_cases

import com.example.passwordsaver.utils.DataStoreUtil
import javax.inject.Inject

class PutPinUseCase @Inject constructor(
    val dataStore: DataStoreUtil
) {
    suspend operator fun invoke(key: String, value: String) = dataStore.save(key, value)
}