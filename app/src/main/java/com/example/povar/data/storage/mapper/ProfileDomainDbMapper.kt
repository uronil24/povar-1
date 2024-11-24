package com.example.povar.data.storage.mapper

import com.example.povar.data.storage.model.ProfileDb
import com.example.povar.domain.model.Profile
import javax.inject.Inject

interface ProfileDomainDbMapper {
    fun toDb(profile: Profile): ProfileDb

    fun toDomain(profileDb: ProfileDb): Profile
}

class ProfileDomainDbMapperImpl @Inject constructor() : ProfileDomainDbMapper{
    override fun toDb(profile: Profile): ProfileDb {
        return ProfileDb(
            name = profile.name,
            avatar = profile.avatar,
            resume = profile.resume
        )
    }

    override fun toDomain(profileDb: ProfileDb): Profile {
        return Profile(
            name = profileDb.name ?: "",
            avatar = profileDb.avatar ?: "",
            resume = profileDb.resume ?: ""
        )
    }

}