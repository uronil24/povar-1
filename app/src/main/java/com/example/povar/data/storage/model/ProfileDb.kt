package com.example.povar.data.storage.model

import kotlinx.serialization.Serializable

@Serializable
data class ProfileDb(
    val name: String? = null,
    val avatar: String? = null,
    val resume: String? = null
)