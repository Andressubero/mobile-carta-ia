package com.example.tp3_petshop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp3_petshop.models.ChangeStatusRequest
import com.example.tp3_petshop.models.DamagePoint
import com.example.tp3_petshop.models.DamageType
import com.example.tp3_petshop.models.EstadoParte
import com.example.tp3_petshop.models.VehicleImage
import com.example.tp3_petshop.models.VehicleState
import com.example.tp3_petshop.models.VehicleStateRequest
import com.example.tp3_petshop.repository.VehicleStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
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

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _selectedDate = MutableStateFlow<String?>(null)
    val selectedDate: StateFlow<String?> = _selectedDate

    private val _estadoPartes = MutableStateFlow<List<EstadoParte>>(emptyList())
    val estadoPartes: StateFlow<List<EstadoParte>> = _estadoPartes

    private val _sides = MutableStateFlow<List<String>>(emptyList())
    val sides: StateFlow<List<String>> = _sides

    private val _images = MutableStateFlow<Map<String, VehicleImage>>(emptyMap())
    val images: StateFlow<Map<String, VehicleImage>> = _images

    fun addImage(sideKey: String, image: VehicleImage) {
        _images.value = _images.value.toMutableMap().apply {
            put(sideKey, image) // reemplaza si ya existe
        }
    }



    fun setDate(date: String) {
        _selectedDate.value = date
    }

    fun setEstadoPartes(list: List<EstadoParte>) {
        _estadoPartes.value = list
    }

    fun addDamage(name: String, damage: DamagePoint) {
        _estadoPartes.update { currentList ->
            currentList.map { parte ->
                if (parte.name == name) {
                    val newDamages = parte.damages
                        .filterNot { it.damageType == DamageType.SIN_DANO } + damage

                    parte.copy(damages = newDamages)
                } else parte
            }
        }
    }

    fun addSide(side: String) {
        _sides.update { currentList ->
            if (side !in currentList) {
                currentList + side
            } else {
                currentList
            }
        }
    }

    init {
        getAll()
    }

    private fun getAll() {
        viewModelScope.launch {
            try {
                val response = repository.getAll()
                if (response.isSuccessful) {
                    _vehicleStates.value = response.body().orEmpty()
                    _errorMessage.value = null
                } else {
                    _errorMessage.value = "Error ${response.code()}: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Exception: ${e.message}"
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
                    _errorMessage.value = null
                } else {
                    _errorMessage.value = "Error al crear: ${response.code()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Exception: ${e.message}"
            }
        }
    }

    fun changeStatus(request: ChangeStatusRequest) {
        viewModelScope.launch {
            try {
                val response = repository.changeStatus(request)
                if (response.isSuccessful) {
                    _changeStatus.value = response.body()
                    _errorMessage.value = null
                } else {
                    _errorMessage.value = "Error al actualizar estado: ${response.code()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Exception: ${e.message}"
            }
        }
    }
}
