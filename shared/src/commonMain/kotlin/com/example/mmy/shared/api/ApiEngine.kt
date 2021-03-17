package com.example.mmy.shared.api

import com.example.mmy.shared.model.Auth
import com.example.mmy.shared.model.Profile
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

class ApiEngine {
    private val httpClient = HttpClient() {
        install(JsonFeature) {
            val json = Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }

    @Throws(Exception::class) suspend fun login(userName: String, password: String): Auth {
        return httpClient.get(PATH_LOGIN)
    }

    @Throws(Exception::class) suspend fun getProfile(userId: String): Profile {
        return httpClient.get(PATH_PROFILE)
    }

    suspend fun updateProfile(profile: Profile): Unit {

    }

    suspend fun logout(userId: String) {

    }

    companion object {
        private const val BASE_URL = "https://6051693a53460900176718d4.mockapi.io"
        private const val PATH_LOGIN = "$BASE_URL/auth/11"
        private const val PATH_PROFILE = "$BASE_URL/profile/1"
    }
}