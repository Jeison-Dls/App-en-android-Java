package com.example.hospitalturnmanagement.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Objeto singleton para gestionar los clientes API
object ApiClient {
    // URL base para mi backend local
    private const val BASE_URL = "http://10.0.2.2:8080/" // Cambia si es necesario
    // URL base para la API de Unsplash
    private const val UNSPLASH_BASE_URL = "https://api.unsplash.com/"
    // URL base para la API de ImgBB
    private const val IMGBB_BASE_URL = "https://api.imgbb.com/1/"

    // Cliente API para el servicio principal de tu backend
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // Configuración de la URL base
            .addConverterFactory(GsonConverterFactory.create()) // Conversor de JSON a objetos Kotlin
            .build()
            .create(ApiService::class.java) // Crea una implementación de la interfaz ApiService
    }

    // Configuración del cliente Retrofit para Unsplash
    private val retrofitUnsplash = Retrofit.Builder()
        .baseUrl(UNSPLASH_BASE_URL) // URL base para Unsplash
        .addConverterFactory(GsonConverterFactory.create()) // Conversor de JSON a objetos Kotlin
        .build()

    // Configuración del cliente Retrofit para ImgBB
    private val retrofitImgbb = Retrofit.Builder()
        .baseUrl(IMGBB_BASE_URL) // URL base para ImgBB
        .addConverterFactory(GsonConverterFactory.create()) // Conversor de JSON a objetos Kotlin
        .build()

    // Servicio API para Unsplash
    val unsplashApiService: UnsplashApiService = retrofitUnsplash.create(UnsplashApiService::class.java)
    // Servicio API para ImgBB
    val imgbbApiService: ImgbbApiService = retrofitImgbb.create(ImgbbApiService::class.java)


}
