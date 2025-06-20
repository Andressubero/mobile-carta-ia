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
class VehicleViewModel @Inject constructor(
    private val repository: VehicleRepository
) : ViewModel() {
    private val _vehicles = MutableStateFlow<List<Vehicle>>(emptyList())
    val vehicles: StateFlow<List<Vehicle>> = _vehicles
    private val _vehicleById = MutableStateFlow<Vehicle?>(null)
    val favoriteById: StateFlow<Vehicle?> = _vehicleById
    private val _vehicleWithPartsById = MutableStateFlow<VehicleDetail?>(null)
    val vehicleWithPartsById: StateFlow<VehicleDetail?> = _vehicleWithPartsById
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error



    fun createVehicle(request: VehicleRequest) = viewModelScope.launch {
        repository.createVehicle(request)
        getById(request.id)
    }

    fun getVehicleWithPartsById(id: String) = viewModelScope.launch {
        try {
            val result = repository.getVehicleWithPartsById(id)
            _vehicleWithPartsById.value = result
            _error.value = null
        } catch (e: Exception) {
            _error.value = "Error al cargar vehículo: ${e.localizedMessage}"
        }
    }


    fun getById(id: String) = viewModelScope.launch {
        _vehicleById.value = repository.getVehicleById(id)
    }

    fun getAll() = viewModelScope.launch{
        _vehicles.value = repository.getAll();
    }

}