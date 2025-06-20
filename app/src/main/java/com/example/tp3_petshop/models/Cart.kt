package com.example.tp3_petshop.models

data class CartRequest(
    val userId: Int,
    val products: List<CartProductRequest>
)

data class CartProductRequest(
    val id: Int,
    val quantity: Int
)

data class CartResponse(
    val id: Int,
    val products: List<CartProductDetail>?,
    val total: Double,
    val totalProducts: Int,
    val totalQuantity: Int
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

data class UpdateCartRequest(
    val merge: Boolean = true,
    val products: List<CartProductRequest>
)
