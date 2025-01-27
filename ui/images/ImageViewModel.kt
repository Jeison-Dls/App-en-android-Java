package com.example.hospitalturnmanagement.ui.images

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalturnmanagement.data.model.UnsplashResponse
import com.example.hospitalturnmanagement.data.repository.ImageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// ViewModel para gestionar la búsqueda de imágenes desde el repositorio
class ImageViewModel(private val repository: ImageRepository) : ViewModel() {
    private val _images = MutableStateFlow<List<UnsplashResponse>>(emptyList())
    val images: StateFlow<List<UnsplashResponse>> get() = _images

    fun searchImages(query: String) {
        viewModelScope.launch {
            try {
                _images.value = repository.searchImages(query)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
