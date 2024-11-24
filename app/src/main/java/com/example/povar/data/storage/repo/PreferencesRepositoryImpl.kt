package com.example.povar.data.storage.repo

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.povar.domain.repo.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : PreferencesRepository {
    override suspend fun set(input: String) {
        dataStore.edit { pref ->
            pref[PreferencesKeys.QUERIES] = input
        }
    }

    override fun get(): Flow<String> {
        return dataStore.data.map { pref ->
            pref[PreferencesKeys.QUERIES] ?: ""
        }
    }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("PREF_NAME")

private object PreferencesKeys {
    val QUERIES = stringPreferencesKey("query")
}