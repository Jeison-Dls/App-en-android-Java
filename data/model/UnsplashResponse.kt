package com.example.hospitalturnmanagement.data.model

import com.google.gson.annotations.SerializedName

// Clase de datos que representa una imagen obtenida desde la API de Unsplash
data class UnsplashResponse(
    @SerializedName("id") val id: String,
    @SerializedName("urls") val urls: Urls,
    @SerializedName("alt_description") val description: String?
)

// Clase de datos que representa las URLs de diferentes tamaños de la imagen
data class Urls(
    @SerializedName("small") val small: String,
    @SerializedName("thumb") val thumb: String
)
