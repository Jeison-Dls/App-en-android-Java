package com.example.hospitalturnmanagement.ui.doctors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalturnmanagement.data.model.Doctor
import com.example.hospitalturnmanagement.data.repository.DoctorsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// ViewModel para gestionar la lógica de negocio de doctores y proporcionar datos a la UI

class DoctorsViewModel(private val repository: DoctorsRepository) : ViewModel() {
    // Estado interno (privado) que contiene la lista de doctores
    private val _doctors = MutableStateFlow<List<Doctor>>(emptyList())
    // Estado público para observar la lista de doctores desde la UI
    val doctors: StateFlow<List<Doctor>> = _doctors

    // Estado interno (privado) que indica si los datos están cargándose
    private val _isLoading = MutableStateFlow(false)
    // Estado público para observar si hay una operación en progreso
    val isLoading: StateFlow<Boolean> = _isLoading

    // obtener la lista de doctores desde el repositorio
    fun fetchDoctors() {
        viewModelScope.launch {
            _isLoading.value = true
            _doctors.value = repository.getDoctors()
            _isLoading.value = false
        }
    }

    // agregar un nuevo doctor
    fun addDoctor(doctor: Doctor) {
        viewModelScope.launch {
            repository.createDoctor(doctor)
            fetchDoctors()
        }
    }
    // actualizar la información de un doctor existente
    fun updateDoctor(id: Int, doctor: Doctor) {
        viewModelScope.launch {
            repository.updateDoctor(id, doctor)
            fetchDoctors()
        }
    }

    // eliminar un doctor por su ID
    fun deleteDoctor(id: Int) {
        viewModelScope.launch {
            repository.deleteDoctor(id)
            fetchDoctors()
        }
    }
}
