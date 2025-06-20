package com.example.tp3_petshop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp3_petshop.models.ChangeStatusRequest
import com.example.tp3_petshop.models.VehicleState
import com.example.tp3_petshop.models.VehicleStateRequest
import com.example.tp3_petshop.repository.VehicleStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehicleStateViewModel @Inject constructor(
    private val repository: VehicleStateRepository
) : ViewModel() {

    private val _vehicleStates = MutableStateFlow<List<VehicleState>>(emptyList())
    val vehicleStates: StateFlow<List<VehicleState>> = _vehicleStates

    private val _changeStatus = MutableStateFlow<VehicleState?>(null)
    val changeStatus: StateFlow<VehicleState?> = _changeStatus


    private val _cartError = MutableStateFlow<String?>(null)
    val cartError: StateFlow<String?> = _cartError

    init {
        getAll()
    }

    private fun getAll() {
        viewModelScope.launch {
            try {
                val response = repository.getAll()
                if (response.isSuccessful) {
                    _vehicleStates.value = response.body().orEmpty()
                } else {
                    _cartError.value = "Error ${response.code()}: ${response.message()}"
                }
            } catch (e: Exception) {
                _cartError.value = "Exception: ${e.message}"
            }
        }
    }

    fun createState(request: VehicleStateRequest) {
        viewModelScope.launch {
            try {
                val response = repository.createState(request)
                if (response.isSuccessful) {
                    // podés hacer un refresh si querés:
                    getAll()
                } else {
                    _cartError.value = "Error al crear: ${response.code()}"
                }
            } catch (e: Exception) {
                _cartError.value = "Exception: ${e.message}"
            }
        }
    }

    fun changeStatus(request: ChangeStatusRequest) {
        viewModelScope.launch {
            try {
                val response = repository.changeStatus(request)
                if (response.isSuccessful) {
                    _changeStatus.value = response.body()
                } else {
                    _cartError.value = "Error al actualizar estado: ${response.code()}"
                }
            } catch (e: Exception) {
                _cartError.value = "Exception: ${e.message}"
            }
        }
    }
}
