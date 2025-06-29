package com.example.tp3_petshop.models

import kotlinx.serialization.Serializable

@Serializable
data class ChangePasswordRequest(
    val password: String,
)