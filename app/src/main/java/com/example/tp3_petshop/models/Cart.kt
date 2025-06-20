package com.example.tp3_petshop.models

data class VehicleStateRequest(
    val userId: Int,
    val products: List<CartProductRequest>
)

data class VehicleRequest(
    val id: String,
)

data class CartProductRequest(
    val id: Int,
    val quantity: Int
)

data class CartProductDetail(
    val id: Int,
    val title: String,
    val price: Double,
    val quantity: Int,
    val total: Double,
    val thumbnail: String,
    val productId: Int
)

data class ChangeStatusRequest(
    val id: String,
    val validation_state: String
)
