package com.example.mmy.shared.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    @SerialName("id")
    val id: String?,
    @SerialName("firstName")
    var firstName: String?,
    @SerialName("lastName")
    var lastName: String?,
    @SerialName("phoneNumber")
    var phoneNumber: String?,
    @SerialName("address")
    var address: String?
)