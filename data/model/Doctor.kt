package com.example.hospitalturnmanagement.data.model

import com.google.gson.annotations.SerializedName

data class Doctor(
    val id: Int? = null, // ID opcional, generado por el backend
    @SerializedName("firstName")
    val firstName: String, // Nombre
    @SerializedName("lastName")
    val lastName: String, // Apellido
    val email: String, // Correo
    val phone: String, // Teléfono
    @SerializedName("availabilityRange")
    val availabilityRange: String, // Rango de disponibilidad (ej. "12:00-16:00")
    val specialty: String, // Especialidad
    val experience: Boolean, // ¿Tiene experiencia?
    val role: String // Rol (se asigna en base al backend)
)
