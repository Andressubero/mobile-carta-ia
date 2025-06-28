package com.example.tp3_petshop.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val username: String,
    val password: String,
)

@Serializable
data class LoginResponse(
    val token: String,
    val id: Int,
    val username: String,
    val email: String? = null
)