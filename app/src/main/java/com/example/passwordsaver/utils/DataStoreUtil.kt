package com.example.passwordsaver.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

import javax.inject.Inject

class DataStoreUtil
@Inject
constructor(
    private val dataStore: DataStore<Preferences>,
    private val security: SecurityUtil
) {
    private val securityKeyAlias = "data-store" //this must come from a env var

    suspend fun save(key: String, value: String) {
        val dataStoreKey = stringPreferencesKey(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = security.encryptData(securityKeyAlias, value).joinToString()
        }
    }

     suspend fun get(key: String): String? {
         val dataStoreKey = stringPreferencesKey(key)
         var data: String? = null
         dataStore.data.map { settings ->
            settings[dataStoreKey]
         }.collect { data = it }

         if (data != null) {
            return security.decryptData(securityKeyAlias, data!!.toByteArray())
         }

         return data
    }
}