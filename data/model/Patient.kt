package com.example.hospitalturnmanagement.data.model

import com.google.gson.annotations.SerializedName
// Clase de datos que representa la información de un paciente
data class Patient(
    val id: Int? = null, // El id ahora es opcional
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("dateOfBirth") val dateOfBirth: String,
    val gender: String,
    val phone: String,
    val email: String,
    val age: Int? = null, // Valor opcional por defecto
    @SerializedName("medicoAsignado") val medicoAsignado: String = "Sin asignar", // Por defecto
    @SerializedName("tipoPrioridad") val tipoPrioridad: String = "Baja" // Por defecto
)
