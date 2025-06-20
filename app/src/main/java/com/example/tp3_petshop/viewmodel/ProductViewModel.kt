package com.example.tp3_petshop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp3_petshop.models.Product
import com.example.tp3_petshop.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class ProductUiState {
    object Loading : ProductUiState()
    data class Success(val products: List<Product>) : ProductUiState()
    data class Error(val message: String) : ProductUiState()
}
@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel()  {

    private val _uiState = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val uiState: StateFlow<ProductUiState> = _uiState

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                val response = repository.getAllProducts()
                _uiState.value = ProductUiState.Success(response.products)
            } catch (e: Exception) {
                _uiState.value = ProductUiState.Error("No se pudieron cargar los productos")
            }
        }
    }
    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?> = _selectedProduct

    fun fetchProductById(id: Int) {
        viewModelScope.launch {
            try {
                val product = repository.getProductById(id)
                _selectedProduct.value = product
            } catch (e: Exception) {
                _selectedProduct.value = null
            }
        }
    }
}