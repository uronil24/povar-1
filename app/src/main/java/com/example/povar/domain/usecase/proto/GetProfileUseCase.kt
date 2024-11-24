package com.example.povar.domain.usecase.proto

import com.example.povar.domain.model.Profile
import com.example.povar.domain.repo.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetProfileUseCase {
    fun getProfile(): Flow<Profile>
}

class GetProfileUseCaseImpl @Inject constructor(
    private val repo: ProfileRepository
) : GetProfileUseCase{
    override fun getProfile(): Flow<Profile> {
        return repo.getProfile()
    }

}