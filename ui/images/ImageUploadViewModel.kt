package com.example.hospitalturnmanagement.ui.images.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.hospitalturnmanagement.data.repository.ImageRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

// ViewModel para gestionar la lógica de subida de imágenes
class ImageUploadViewModel(private val repository: ImageRepository) : ViewModel() {
    private val _uploadResult = MutableStateFlow<String?>(null)
    val uploadResult: StateFlow<String?> get() = _uploadResult.asStateFlow()

    // Función para subir una imagen al servidor
    fun uploadImage(apiKey: String, file: File) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Convertir el archivo en un MultipartBody.Part
                val requestFile = file.asRequestBody()
                val multipartBody = MultipartBody.Part.createFormData("image", file.name, requestFile)

                // Llamar al repositorio
                val response = repository.uploadImage(apiKey, multipartBody)
                _uploadResult.value = response.data.displayUrl
            } catch (e: Exception) {
                _uploadResult.value = "Error: ${e.message}"
            }
        }
    }


}
