package com.example.tp3_petshop.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp3_petshop.models.Vehicle
import com.example.tp3_petshop.models.VehicleDetail
import com.example.tp3_petshop.models.VehicleRequest
import com.example.tp3_petshop.repository.VehicleRepository
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class FavoriteProductViewModel @Inject constructor(
    private val repository: VehicleRepository
) : ViewModel() {
    private val _favoritesStateFlow = MutableStateFlow<List<Vehicle>>(emptyList())
    val favoritesStateFlow: StateFlow<List<Vehicle>> = _favoritesStateFlow
    private val _vehicleById = MutableStateFlow<Vehicle?>(null)
    val favoriteById: StateFlow<Vehicle?> = _vehicleById
    private val _vehicleWithPartsById = MutableStateFlow<VehicleDetail?>(null)
    val vehicleWithPartsById: StateFlow<VehicleDetail?> = _vehicleWithPartsById


    fun createVehicle(request: VehicleRequest) = viewModelScope.launch {
        repository.createVehicle(request)
        getById(request.id)
    }

    fun getVehicleWithPartsById(id: String) = viewModelScope.launch {
        _vehicleWithPartsById.value = repository.getVehicleWithPartsById(id)
    }

    fun getById(id: String) = viewModelScope.launch {
        _vehicleById.value = repository.getVehicleById(id)
    }

}