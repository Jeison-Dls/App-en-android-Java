package com.example.hospitalturnmanagement.ui.menu

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.material3.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuScreen(
    onNavigateToMaintenance: () -> Unit, // Callback para navegar a Mantenimientos
    onNavigateToConsultations: () -> Unit, // Callback para navegar a Consultas
    onNavigateToImageSearch: () -> Unit, // Callback para navegar a Buscar Imágenes
    onNavigateToImageUpload: () -> Unit, // Callback para navegar a Subir Imágenes
    onLogout: () -> Unit // Callback para cerrar sesión
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Hospital Turn Management") },
                actions = {
                    TextButton(onClick = onLogout) {
                        Text(
                            text = "Cerrar Sesión",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                content = {
                    Text(
                        text = "Hospital Turn Management",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // New text above "Menú Principal"
            Text(
                text = "~Bienvenido~",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp, bottom = 16.dp) // Adjust spacing
            )

            // Title "Menú Principal"
            Text(
                text = "Menú Principal",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 32.dp) // Moved lower to make space for the new text
            )

            // Centered buttons
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = onNavigateToMaintenance,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(80.dp)
                            .padding(8.dp)
                    ) {
                        Text(text = "Mantenimientos", textAlign = TextAlign.Center)
                    }
                    Button(
                        onClick = onNavigateToConsultations,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(80.dp)
                            .padding(8.dp)
                    ) {
                        Text(text = "Consultas", textAlign = TextAlign.Center)
                    }
                }

                // Spacer between the two rows
                Spacer(modifier = Modifier.height(24.dp)) // Adjust the height to separate the rows

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = onNavigateToImageSearch,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(80.dp)
                            .padding(8.dp)
                    ) {
                        Text(text = "Buscar Imágenes", textAlign = TextAlign.Center)
                    }
                    Button(
                        onClick = onNavigateToImageUpload,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(80.dp)
                            .padding(8.dp)
                    ) {
                        Text(text = "Subir Imágenes", textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}
