package com.example.tp3_petshop.repository

import android.util.Log
import com.example.tp3_petshop.dao.FavoriteProductDao
import com.example.tp3_petshop.models.FavoriteProduct
import com.example.tp3_petshop.models.FavoriteProductDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteProductRepository @Inject constructor(
    private val favoriteProductDao: FavoriteProductDao
) {
    private fun FavoriteProduct.toDto(): FavoriteProductDto {
        return FavoriteProductDto(productId)
    }

    private fun FavoriteProductDto.toEntity(): FavoriteProduct {
        Log.d("DEBUG", "Pasando a entity: $productId")
        return FavoriteProduct(uid = 0, productId)
    }

    suspend fun getAllFavorites(): List<FavoriteProductDto> {
        return favoriteProductDao.getAll().map { it.toDto() }
    }

    suspend fun addFavorite(productDto: FavoriteProductDto) {
        Log.d("DEBUG", "Insertando en repository: $productDto")
        favoriteProductDao.insertAll(productDto.toEntity())
    }

    suspend fun deleteFavorite(productDto: FavoriteProductDto) {
        productDto.productId?.let { favoriteProductDao.delete(it) }
    }

    suspend fun getByProductId(id: Int): FavoriteProductDto? {
        return favoriteProductDao.getByProductId(id)?.toDto()
    }
}