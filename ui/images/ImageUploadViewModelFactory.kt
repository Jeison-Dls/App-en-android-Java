package com.example.hospitalturnmanagement.ui.images.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hospitalturnmanagement.data.repository.ImageRepository


// Factoría personalizada para crear instancias de ImageUploadViewModel
class ImageUploadViewModelFactory(private val repository: ImageRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImageUploadViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ImageUploadViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
