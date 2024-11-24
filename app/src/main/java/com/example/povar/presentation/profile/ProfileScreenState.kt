package com.example.povar.presentation.profile

import android.net.Uri

interface ProfileScreenState {
    val name: String
    val resumeUri: Uri
    val avatarUri: Uri
}