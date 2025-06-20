package com.example.tp3_petshop.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tp3_petshop.dao.FavoriteProductDao
import com.example.tp3_petshop.models.FavoriteProduct

@Database(entities = [FavoriteProduct::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteProductDao(): FavoriteProductDao
}