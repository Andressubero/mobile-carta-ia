package com.example.tp3_petshop.repository

import com.example.tp3_petshop.models.Product
import com.example.tp3_petshop.models.ProductResponse
import com.example.tp3_petshop.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor() {
    suspend fun getAllProducts(): ProductResponse {
        return withContext(Dispatchers.IO) {
            RetrofitInstance.api.getAllProducts()
        }
    }

    suspend fun getProductById(id: Int): Product {
        return withContext(Dispatchers.IO) {
            RetrofitInstance.api.getProductById(id)
        }
    }
}