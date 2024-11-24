package com.example.povar.domain.usecase.preference

import com.example.povar.domain.repo.PreferencesRepository
import javax.inject.Inject

interface SetPreferencesUseCase{
    suspend fun set(input: String)
}

class SetPreferencesUseCaseImpl @Inject constructor(
    private val repo: PreferencesRepository
) : SetPreferencesUseCase{
    override suspend fun set(input: String) {
        repo.set(input)
    }
}