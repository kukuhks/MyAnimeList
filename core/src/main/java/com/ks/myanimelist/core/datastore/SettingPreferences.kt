package com.ks.myanimelist.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "setting")
class SettingPreferences(private val dataStore: Context) {

    companion object {
        private val DARK_MODE_KEY = booleanPreferencesKey("theme_setting")
    }

    fun getThemeSetting(): Flow<Boolean> {
        return dataStore.dataStore.data.map { preferences ->
            preferences[DARK_MODE_KEY] ?: false
        }
            .distinctUntilChanged()
    }

     suspend fun saveThemeSetting(enabled: Boolean) {
        dataStore.dataStore.edit { preferences ->
            preferences[DARK_MODE_KEY] = enabled
        }
    }
}