package com.example.povar.data.storage.repo

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.povar.data.storage.mapper.ProfileDomainDbMapper
import com.example.povar.data.storage.mapper.ProfileDomainDbMapperImpl
import com.example.povar.data.storage.model.ProfileDb
import com.example.povar.data.storage.util.ProfileSerializer
import com.example.povar.domain.model.Profile
import com.example.povar.domain.repo.ProfileRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val mapper: ProfileDomainDbMapper
) : ProfileRepository {
    override fun getProfile(): Flow<Profile> {
        return context.protoDataStore.data.map { data ->
            mapper.toDomain(data)
        }
    }

    override suspend fun setProfile(profile: Profile) {
        context.protoDataStore.updateData { data ->
            data.copy(
                name = profile.name,
                avatar = profile.avatar,
                resume = profile.resume
            )
        }
    }
}

private val Context.protoDataStore by dataStore("PROFILE_DATA_STORE", ProfileSerializer)