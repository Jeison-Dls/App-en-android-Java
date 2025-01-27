package com.example.hospitalturnmanagement.data.repository

import com.example.hospitalturnmanagement.data.api.ImgbbApiService
import com.example.hospitalturnmanagement.data.api.UnsplashApiService
import com.example.hospitalturnmanagement.data.model.UnsplashResponse
import com.example.hospitalturnmanagement.data.model.ImgbbResponse
import okhttp3.MultipartBody

class ImageRepository(
    private val unsplashApiService: UnsplashApiService,
    private val imgbbApiService: ImgbbApiService
) {
    private val unsplashApiKey = "1dkzjjR-fRGgxfntrxbslY00c2ZZbg3Q9IYOWfCn32c"
    //private val imgbbApiKey = "8b14d10298ce569954d3defe13883e1e" // clave de IMGBB

    // Búsqueda de imágenes en Unsplash
    suspend fun searchImages(query: String): List<UnsplashResponse> {
        val response = unsplashApiService.searchImages(query = query, clientId = unsplashApiKey)
        return response.results
    }

    // Subida de imágenes a IMGBB
    suspend fun uploadImage(apiKey: String, image: MultipartBody.Part): ImgbbResponse {
        return imgbbApiService.uploadImage(apiKey, image)
    }
}
