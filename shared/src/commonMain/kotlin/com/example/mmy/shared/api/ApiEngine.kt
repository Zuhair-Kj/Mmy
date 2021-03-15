package com.example.mmy.shared.api

import com.example.mmy.shared.model.Auth
import com.example.mmy.shared.model.Profile
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json

class ApiEngine {
    private val httpClient = HttpClient() {
        install(JsonFeature) {
            val json = Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }

    suspend fun login(userName: String, password: String): Auth {
        return Auth("1", null)
//        return httpClient.get(PATH_LOGIN)
    }

    suspend fun getProfile(userId: String): Profile {
        return Profile("1", "Jack", "Jefferson", "123123", "Kuala Lumpur, Malaysia")
//        return httpClient.get(PATH_PROFILE)
    }

    suspend fun updateProfile(profile: Profile) {

    }

    suspend fun logout(userId: String) {

    }

    companion object {
        private const val BASE_URL = "https://blah.com"
        private const val PATH_LOGIN = "$BASE_URL/login"
        private const val PATH_PROFILE = "$BASE_URL/profile"
    }
}