package com.example.xpenses.mainModule.model

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.xpenses.common.entities.UserPreferences
import com.example.xpenses.common.utils.Constants
import com.example.xpenses.common.utils.OptionsMenu
import kotlinx.coroutines.flow.first
import java.lang.Exception
import javax.inject.Inject

class DataStoreMain @Inject constructor(private val dataStore: DataStore<Preferences>) {
    suspend fun fetchInitialPreferences() =
        mapUserPreferences(dataStore.data.first().toPreferences())

    private fun mapUserPreferences(preferences: Preferences): UserPreferences {
        val lastDestination = try {
            preferences[Constants.K_LAST_DESTINATION] ?: OptionsMenu.TODAY.name
        } catch (e: Exception) {
            OptionsMenu.TODAY.name
        }
        return UserPreferences(lastDestination)
    }

    suspend fun updateLastDestination(lastDestination: String) {
        dataStore.edit { preferences ->
            preferences[Constants.K_LAST_DESTINATION] = lastDestination
        }
    }
}