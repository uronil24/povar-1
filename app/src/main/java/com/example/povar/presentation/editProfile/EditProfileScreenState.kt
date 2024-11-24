package com.example.povar.presentation.editProfile

import android.net.Uri
import java.time.LocalTime

interface EditProfileScreenState {
    val name: String
    val avatarUri: Uri
    val resumeUri: Uri
    val time: LocalTime
    val timeString: String
    val timeError: String?
    val isNeedToShowPermission: Boolean
    val isNeedToShowSelect: Boolean
    val isNeedToOpenTimer: Boolean
}