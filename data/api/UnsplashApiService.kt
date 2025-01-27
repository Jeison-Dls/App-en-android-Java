package com.example.hospitalturnmanagement.data.api

import com.example.hospitalturnmanagement.data.model.UnsplashResponse
import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Query

// Interfaz para interactuar con la API de Unsplash
interface UnsplashApiService {
    // Método para buscar imágenes en Unsplash
    @GET("search/photos") // Define una solicitud GET al endpoint "search/photos"
    suspend fun searchImages(
        @Query("query") query: String, // Parámetro de consulta para buscar imágenes basadas en una palabra clave
        @Query("page") page: Int = 1, // Parámetro opcional para la paginación, valor por defecto es 1
        @Query("per_page") perPage: Int = 10, // Número de resultados por página, valor por defecto es 10
        @Query("client_id") clientId: String = "1dkzjjR-fRGgxfntrxbslY00c2ZZbg3Q9IYOWfCn32c"
        // Clave de cliente para autenticar la solicitud.
        // IMPORTANTE: Esta clave es sensible, utiliza variables de entorno o almacenamiento seguro en producción.
    ): UnsplashSearchResult
}

// Clase de datos para representar la respuesta de búsqueda de Unsplash
data class UnsplashSearchResult(
    @SerializedName("results") val results: List<UnsplashResponse>
    // La lista de imágenes encontradas, mapeada al campo "results" del JSON de respuesta
)
