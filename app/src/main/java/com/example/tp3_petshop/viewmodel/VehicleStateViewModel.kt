package com.example.tp3_petshop.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp3_petshop.models.ChangeStatusRequest
import com.example.tp3_petshop.models.DamagePoint
import com.example.tp3_petshop.models.DamageType
import com.example.tp3_petshop.models.EstadoParte
import com.example.tp3_petshop.models.VehicleImage
import com.example.tp3_petshop.models.VehicleState
import com.example.tp3_petshop.repository.VehicleStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class VehicleStateViewModel @Inject constructor(
    private val repository: VehicleStateRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _vehicleStates = MutableStateFlow<List<VehicleState>>(emptyList())
    val vehicleStates: StateFlow<List<VehicleState>> = _vehicleStates

    private val _changeStatus = MutableStateFlow<VehicleState?>(null)
    val changeStatus: StateFlow<VehicleState?> = _changeStatus

    private val _isFirst = MutableStateFlow<Boolean>(false)
    val isFirst: StateFlow<Boolean> = _isFirst

    private val _stateDetail = MutableStateFlow<VehicleState?>(null)
    val stateDetail: StateFlow<VehicleState?> = _stateDetail

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

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

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

    fun addSides(sides: List<String>) {
        _sides.update { currentList ->
            val nuevos = sides.filterNot { it in currentList }
            currentList + nuevos
        }
    }

    fun getAll() {
        viewModelScope.launch {
            try {
                val response = repository.getAll()
                if (response.isSuccessful) {
                    response.body()?.let { responseBody ->
                        _vehicleStates.value = responseBody
                    } ?: run {
                        _errorMessage.value = "El cuerpo de la respuesta está vacío"
                    }
                } else {
                    _errorMessage.value = "Error ${response.code()}: ${response.message()}"
                }
            } catch (e: Exception) {
                println("Error en getAll: ${e.message}")
                _errorMessage.value = "Exception: ${e.message}"
            }
        }
    }


    fun createVehicleState(
        vehicleId: String,
        brand: String,
        model: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.create(
                    context = context,
                    vehicleId = vehicleId,
                    date = selectedDate.value!!,
                    brand = brand,
                    model = model,
                    estadoPartes = _estadoPartes.value,
                    images = _images.value
                )

                if (response.isSuccessful) {
                    println("✅ Estado creado correctamente")
                    onSuccess() // <-- solo si fue exitoso
                } else {
                    _errorMessage.value = "Error al actualizar estado: ${response.code()}"
                    println("❌ Error: ${response.code()} - ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                println("❌ Excepción: ${e.stackTraceToString()}")
                _errorMessage.value = "Error al actualizar estado: ${e.message}"
            } finally {
                _isLoading.value = false
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
    fun isFirstState(id: String) {
        viewModelScope.launch {
            try {
                val response = repository.isFirstState(id)
                if (response.isSuccessful) {
                    var result = response.body()?.isFirst
                    println("Respuesta del endpoint isFirstState:$result")
                    _isFirst.value = result == true
                    _errorMessage.value = null
                } else {
                    _errorMessage.value = "Error al actualizar estado: ${response.code()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Exception: ${e.message}"
            }
        }
    }
    fun getById(id: String) {
        viewModelScope.launch {
            try {
                val response = repository.getById(id)
                if (response.isSuccessful) {
                    _stateDetail.value = response.body()
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
