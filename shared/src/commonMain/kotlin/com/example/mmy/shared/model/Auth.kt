package com.example.mmy.shared.model

import kotlinx.serialization.Serializable

@Serializable
data class Auth(
    val id: String?,
    val message: String?
)