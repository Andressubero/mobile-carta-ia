package com.example.tp3_petshop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp3_petshop.models.Product
import com.example.tp3_petshop.repository.FavoriteProductRepository
import com.example.tp3_petshop.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CombinedViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val favoriteRepository: FavoriteProductRepository
) : ViewModel() {

    private val _favoriteProducts = MutableStateFlow<List<Product>>(emptyList())
    val favoriteProducts: StateFlow<List<Product>> = _favoriteProducts

    private val allProducts = MutableStateFlow<List<Product>>(emptyList())

    init {
        loadAllProductsAndFavorites()
    }

    private fun loadAllProductsAndFavorites() {
        viewModelScope.launch {
            try {
                // Obtener todos los productos
                val productsResponse = productRepository.getAllProducts()
                val products = productsResponse.products
                allProducts.value = products

                // Obtener favoritos
                val favorites = favoriteRepository.getAllFavorites()
                val favoriteIds = favorites.mapNotNull { it.productId }

                // Filtrar productos favoritos
                _favoriteProducts.value = products.filter { it.id in favoriteIds }
            } catch (e: Exception) {
                _favoriteProducts.value = emptyList()
            }
        }
    }

    fun refresh() = loadAllProductsAndFavorites()
}
