package com.example.tp3_petshop.models

import kotlinx.serialization.Serializable

@Serializable
data class Login(
    val username: String,
    val password: String,
    val expiresInMins: Int = 30
)

@Serializable
data class LoginResponse(
    val token: String,
    val id: Int,
    val username: String,
    val email: String? = null
)

data class LogoutResponse(
    val message: String
)