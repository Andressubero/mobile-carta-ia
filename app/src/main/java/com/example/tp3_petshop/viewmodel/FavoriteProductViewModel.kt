package com.example.tp3_petshop.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.tp3_petshop.models.FavoriteProductDto
import com.example.tp3_petshop.repository.FavoriteProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class FavoriteProductViewModel @Inject constructor(
    private val repository: FavoriteProductRepository
) : ViewModel() {
    private val _favoritesStateFlow = MutableStateFlow<List<FavoriteProductDto>>(emptyList())
    val favoritesStateFlow: StateFlow<List<FavoriteProductDto>> = _favoritesStateFlow
    private val _favoriteById = MutableStateFlow<FavoriteProductDto?>(null)
    val favoriteById: StateFlow<FavoriteProductDto?> = _favoriteById

    fun getFavorites() {
        viewModelScope.launch {
            val favorites = repository.getAllFavorites()
            _favoritesStateFlow.value = favorites
        }
    }

    fun saveFavorite(productDto: FavoriteProductDto) = viewModelScope.launch {
        Log.d("DEBUG", "Guardando favorito: $productDto")
        repository.addFavorite(productDto)
        productDto.productId?.let { getByProductId(it) }
    }

    fun deleteFavorite(productDto: FavoriteProductDto) = viewModelScope.launch {
        repository.deleteFavorite(productDto)
        productDto.productId?.let { getByProductId(it) }
    }

    fun getByProductId(id: Int) = viewModelScope.launch {
        _favoriteById.value = repository.getByProductId(id)
    }

}