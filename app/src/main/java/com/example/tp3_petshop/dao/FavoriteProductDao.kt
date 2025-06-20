package com.example.tp3_petshop.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tp3_petshop.models.FavoriteProduct

@Dao
interface FavoriteProductDao {
    @Query("SELECT * FROM favorite_products")
    suspend fun getAll(): List<FavoriteProduct>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg products: FavoriteProduct)

    @Query("DELETE FROM favorite_products WHERE product_id = :productId")
    suspend fun delete(productId: Int)

    @Query("SELECT * FROM favorite_products WHERE product_id = :id")
    suspend fun getByProductId(id: Int): FavoriteProduct?

}