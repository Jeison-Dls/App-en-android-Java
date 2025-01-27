package com.example.hospitalturnmanagement.ui.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.hospitalturnmanagement.data.api.ApiClient
import com.example.hospitalturnmanagement.data.model.UnsplashResponse
import com.example.hospitalturnmanagement.data.repository.ImageRepository

@Composable
fun ImageSearchScreen() {
    // Crea una instancia del repositorio con los servicios necesarios
    val repository = ImageRepository(
        unsplashApiService = ApiClient.unsplashApiService, // Servicio de Unsplash
        imgbbApiService = ApiClient.imgbbApiService // Servicio de IMGBB
    )
    // Crea una instancia del ViewModel asociado al repositorio
    val viewModel = ImageViewModel(repository)

    // Estado para el texto de búsqueda
    var query by remember { mutableStateOf("") }
    // Estado para observar la lista de imágenes desde el ViewModel
    val images by viewModel.images.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Buscar imágenes") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { viewModel.searchImages(query) }) {
            Text("Buscar")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(images) { image ->
                ImageItem(image)
            }
        }
    }
}

@Composable
fun ImageItem(image: UnsplashResponse) {
    // Elemento individual para mostrar una imagen
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = image.description ?: "Sin descripción")
        Image(
            painter = rememberImagePainter(data = image.urls.small),
            contentDescription = image.description,
            modifier = Modifier.size(128.dp)
        )
    }
}

/*GET /search/photos (Unsplash): Devuelve imágenes basadas en una consulta de búsqueda.
POST /upload (IMGBB): Sube imágenes al servidor.*/
