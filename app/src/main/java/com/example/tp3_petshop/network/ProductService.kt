package com.example.tp3_petshop.network

import com.example.tp3_petshop.models.Product
import com.example.tp3_petshop.models.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {
    @GET("products")
    suspend fun getAllProducts(): ProductResponse

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product

}