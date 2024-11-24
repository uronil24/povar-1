package com.example.povar.domain.repo

import com.example.povar.domain.model.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getProfile() : Flow<Profile>

    suspend fun setProfile(profile: Profile)
}