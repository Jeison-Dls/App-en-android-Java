package com.example.hospitalturnmanagement.data.model

import com.google.gson.annotations.SerializedName

data class Appointment(
    val id: Int? = null, // ID del turno (opcional)
    @SerializedName("patient_id") val patientId: Int, // ID del paciente
    @SerializedName("doctor_id") val doctorId: Int, // ID del doctor
    @SerializedName("appointment_date") val appointmentDate: String, // Fecha del turno
    val status: String, // Estado del turno (e.g., "pendiente", "completado")



    // Campos adicionales para mostrar nombres en la tabla
    var doctorName: String? = null,
    var patientName: String? = null

)
