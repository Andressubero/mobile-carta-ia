package com.example.tp3_petshop.repository

import com.example.tp3_petshop.models.CartProductRequest
import com.example.tp3_petshop.models.CartRequest
import com.example.tp3_petshop.models.CartResponse
import com.example.tp3_petshop.models.UpdateCartRequest
import com.example.tp3_petshop.network.RetrofitInstance
import retrofit2.Response

class CartRepository {

    suspend fun addToCart(request: CartRequest): Response<CartResponse> {
        return RetrofitInstance.cartApi.addToCart(request)
    }

    suspend fun getUserCart(): Response<CartResponse> {
        return RetrofitInstance.cartApi.getUserCart()
    }

    suspend fun updateCart(cartId: Int, products: List<CartProductRequest>): Response<CartResponse> {
        val request = UpdateCartRequest(
            merge = true,
            products = products
        )
        return RetrofitInstance.cartApi.updateCart(cartId, request)
    }

}