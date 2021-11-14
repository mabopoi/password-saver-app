package com.example.passwordsaver.domain.use_cases

import com.example.passwordsaver.utils.DataStoreUtil
import javax.inject.Inject

class GetPinUseCase @Inject constructor(
    val dataStore: DataStoreUtil
) {
    suspend operator fun invoke(key: String): String?{
        return dataStore.get(key)
    }
}