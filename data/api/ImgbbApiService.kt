package com.example.hospitalturnmanagement.data.api

import com.example.hospitalturnmanagement.data.model.ImgbbResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

// Interfaz para interactuar con la API de ImgBB
interface ImgbbApiService {
    // Método para subir una imagen al servidor de ImgBB
    @Multipart // Indica que esta solicitud enviará datos multipart (usado para subir archivos)
    @POST("upload") // Define que la solicitud será un POST al endpoint "/upload"
    suspend fun uploadImage(
        @Query("key") apiKey: String, // Parámetro de consulta (query) para enviar la API key
        @Part image: MultipartBody.Part // Parte del cuerpo de la solicitud que contiene la imagen
    ): ImgbbResponse


}
