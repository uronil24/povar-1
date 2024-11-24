package com.example.povar.domain.usecase.preference

import com.example.povar.domain.repo.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetPreferencesUseCase {
    fun get(): Flow<String>
}

class GetPreferencesUseCaseImpl @Inject constructor(
    private val repo: PreferencesRepository
): GetPreferencesUseCase{
    override fun get(): Flow<String> {
        return repo.get()
    }
}