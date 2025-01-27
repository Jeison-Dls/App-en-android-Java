package com.example.hospitalturnmanagement.ui.turnos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalturnmanagement.data.model.Appointment
import com.example.hospitalturnmanagement.data.model.Doctor
import com.example.hospitalturnmanagement.data.model.Patient
import com.example.hospitalturnmanagement.data.repository.TurnosRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TurnosViewModel(private val repository: TurnosRepository) : ViewModel() {

    // Estado para la lista de turnos
    private val _appointments = MutableStateFlow<List<Appointment>>(emptyList())
    val appointments: StateFlow<List<Appointment>> get() = _appointments

    // Estado para la lista de médicos
    private val _doctors = MutableStateFlow<List<Doctor>>(emptyList())
    val doctors: StateFlow<List<Doctor>> get() = _doctors

    // Estado para la lista de pacientes
    private val _patients = MutableStateFlow<List<Patient>>(emptyList())
    val patients: StateFlow<List<Patient>> get() = _patients

    // Cargar turnos
    fun fetchAppointments() {
        viewModelScope.launch {
            try {
                val fetchedAppointments = repository.getAppointments()
                val fetchedDoctors = repository.getDoctors()
                val fetchedPatients = repository.getPatients()

                _appointments.value = fetchedAppointments.map { appointment ->
                    val doctorName = fetchedDoctors.find { it.id == appointment.doctorId }
                        ?.let { "${it.firstName} ${it.lastName}" } ?: "Desconocido"
                    val patientName = fetchedPatients.find { it.id == appointment.patientId }
                        ?.let { "${it.firstName} ${it.lastName}" } ?: "Desconocido"

                    appointment.copy(doctorName = doctorName, patientName = patientName)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Cargar médicos
    fun fetchDoctors() {
        viewModelScope.launch {
            try {
                _doctors.value = repository.getDoctors()
            } catch (e: Exception) {
                e.printStackTrace() // Manejo de errores
            }
        }
    }

    // Cargar pacientes
    fun fetchPatients() {
        viewModelScope.launch {
            try {
                _patients.value = repository.getPatients()
            } catch (e: Exception) {
                e.printStackTrace() // Manejo de errores
            }
        }
    }

    // Crear un turno
    fun createAppointment(appointment: Appointment) {
        viewModelScope.launch {
            try {
                repository.createAppointment(appointment)
                fetchAppointments() // Actualizar lista
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Actualizar un turno
    fun updateAppointment(appointmentId: Int, status: String) {
        viewModelScope.launch {
            try {
                repository.updateAppointment(appointmentId, status)
                fetchAppointments()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Eliminar un turno
    fun deleteAppointment(appointmentId: Int) {
        viewModelScope.launch {
            try {
                repository.deleteAppointment(appointmentId)
                fetchAppointments()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
