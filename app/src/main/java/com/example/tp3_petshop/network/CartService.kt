package com.example.tp3_petshop.network

import com.example.tp3_petshop.models.CartRequest
import com.example.tp3_petshop.models.CartResponse
import com.example.tp3_petshop.models.UpdateCartRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CartService {
    @POST("carts/add")
    suspend fun addToCart(@Body request: CartRequest): Response<CartResponse>

    @GET("carts/1")
    suspend fun getUserCart(): Response<CartResponse>

    @PUT("carts/{cartId}")
    suspend fun updateCart(
        @Path("cartId") cartId: Int,
        @Body request: UpdateCartRequest
    ): Response<CartResponse>


    @DELETE("carts/{cartId}")
    suspend fun deleteCart(@Path("cartId") cartId: Int): Response<Unit>

}
