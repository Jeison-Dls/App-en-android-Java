package com.example.hospitalturnmanagement.ui.patients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalturnmanagement.data.model.Patient
import com.example.hospitalturnmanagement.data.repository.PatientsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// ViewModel para gestionar la lógica de negocio relacionada con pacientes
class PatientsViewModel(private val repository: PatientsRepository) : ViewModel() {
    // Estado interno mutable para almacenar la lista de pacientes
    private val _patients = MutableStateFlow<List<Patient>>(emptyList())
    // Estado público inmutable para que la UI observe la lista de pacientes
    val patients: StateFlow<List<Patient>> = _patients

    // Estado interno mutable para indicar si los datos están cargándose
    private val _isLoading = MutableStateFlow(false)
    // Estado público inmutable para que la UI observe el estado de carga
    val isLoading: StateFlow<Boolean> = _isLoading

    //lista de pacientes desde el repositorio
    fun fetchPatients() {
        viewModelScope.launch {
            _isLoading.value = true // Indica que la carga ha comenzado
            _patients.value = repository.getPatients()
            _isLoading.value = false
        }
    }

    // agregar un nuevo paciente
    fun addPatient(patient: Patient) {
        viewModelScope.launch {
            repository.createPatient(patient)
            fetchPatients()
        }
    }

    // actualizar los datos de un paciente existente
    fun updatePatient(id: Int, patient: Patient) {
        viewModelScope.launch {
            repository.updatePatient(id, patient)
            fetchPatients()
        }
    }

    // eliminar un paciente por su ID
    fun deletePatient(id: Int) {
        viewModelScope.launch {
            repository.deletePatient(id)
            fetchPatients()
        }
    }
}
