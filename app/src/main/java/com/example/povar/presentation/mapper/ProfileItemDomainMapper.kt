package com.example.povar.presentation.mapper

import android.net.Uri
import com.example.povar.domain.model.Profile
import com.example.povar.presentation.model.ProfileItem
import javax.inject.Inject

interface ProfileItemDomainMapper {
    fun toItem(profile: Profile) : ProfileItem

    fun toDomain(profileItem: ProfileItem) : Profile
}

class ProfileItemDomainMapperImpl @Inject constructor(): ProfileItemDomainMapper{
    override fun toItem(profile: Profile): ProfileItem {
        return ProfileItem(
            name = profile.name.ifEmpty { "Нет имени" },
            avatarUri = Uri.parse(profile.avatar),
            resumeUri = Uri.parse(profile.resume)
        )
    }

    override fun toDomain(profileItem: ProfileItem): Profile {
        return Profile(
            name = if (profileItem.name == "Нет имени") "" else profileItem.name,
            avatar = profileItem.avatarUri.toString(),
            resume = profileItem.resumeUri.toString()
        )
    }

}