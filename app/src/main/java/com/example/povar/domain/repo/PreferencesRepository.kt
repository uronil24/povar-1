package com.example.povar.domain.repo

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    suspend fun set(input: String)

    fun get() : Flow<String>
}