package com.example.tp3_petshop.models

import kotlinx.serialization.Serializable

@Serializable
data class Register(
    val username: String,
    val email: String,
    val password: String,
)

@Serializable
data class RegisterResponse(
    val success: Boolean,
    val message: String
)