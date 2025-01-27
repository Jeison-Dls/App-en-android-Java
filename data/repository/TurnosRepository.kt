package com.example.hospitalturnmanagement.data.repository

import com.example.hospitalturnmanagement.data.api.ApiService
import com.example.hospitalturnmanagement.data.model.Appointment
import com.example.hospitalturnmanagement.data.model.Doctor
import com.example.hospitalturnmanagement.data.model.Patient

class TurnosRepository(private val apiService: ApiService) {

    // Obtener todos los turnos
    suspend fun getAppointments(): List<Appointment> = apiService.getAppointments()

    // Crear un turno
    suspend fun createAppointment(appointment: Appointment): Appointment =
        apiService.createAppointment(appointment).body()!!

    // Actualizar un turno
    suspend fun updateAppointment(appointmentId: Int, status: String) {
        apiService.updateAppointment(appointmentId, mapOf("status" to status))
    }

    // Eliminar un turno
    suspend fun deleteAppointment(appointmentId: Int) {
        apiService.deleteAppointment(appointmentId)
    }

    //Obtener Doctores
    suspend fun getDoctors(): List<Doctor> {
        return apiService.getDoctors()
    }

    //Obtener Pacientes
    suspend fun getPatients(): List<Patient> {
        return apiService.getPatients()
    }
}
