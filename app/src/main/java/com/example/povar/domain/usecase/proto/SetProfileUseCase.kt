package com.example.povar.domain.usecase.proto

import com.example.povar.domain.model.Profile
import com.example.povar.domain.repo.ProfileRepository
import javax.inject.Inject

interface SetProfileUseCase {
    suspend fun setProfile(profile: Profile)
}

class SetProfileUseCaseImpl @Inject constructor(
    private val repo: ProfileRepository
) : SetProfileUseCase{
    override suspend fun setProfile(profile: Profile) {
        repo.setProfile(profile)
    }

}