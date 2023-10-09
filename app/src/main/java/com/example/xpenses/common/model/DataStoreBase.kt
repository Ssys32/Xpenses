package com.example.xpenses.common.model

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.xpenses.common.utils.Constants
import javax.inject.Inject

class DataStoreBase @Inject constructor(private val appContext: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = Constants.KEY_USER_PREFERENCES,
        produceMigrations = { context ->
            listOf(SharedPreferencesMigration(context, Constants.KEY_USER_PREFERENCES))
        }
    )

    val dataStore: DataStore<Preferences>
        get() = this.appContext.dataStore
}