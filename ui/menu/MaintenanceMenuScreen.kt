package com.example.hospitalturnmanagement.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaintenanceMenuScreen(
    onNavigateToPatientMaintenance: () -> Unit, // Callback para navegar a la pantalla de mantenimiento de pacientes
    onNavigateToDoctorMaintenance: () -> Unit, // Callback para navegar a la pantalla de mantenimiento de médicos
    onNavigateToTurnos: () -> Unit // Callback para navegar a la pantalla de turnos
) {
    Scaffold(
        topBar = {
            CenteredTopAppBar(title = "Mantenimientos")
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
            Spacer(modifier = Modifier.height(16.dp))

            // Título superior
            Text(
                text = "~Bienvenido~",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Subtítulo
            Text(
                text = "Menú de Mantenimientos",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Diseño de botones en cuadrícula
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CustomButton(
                        text = "Pacientes",
                        onClick = onNavigateToPatientMaintenance
                    )
                    CustomButton(
                        text = "Médicos",
                        onClick = onNavigateToDoctorMaintenance
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CustomButton(
                        text = "Turnos",
                        onClick = onNavigateToTurnos
                    )
                }
            }
        }
    }
}

// Composable para personalizar botones
@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(150.dp)
            .height(100.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0)), // Color azul
        shape = MaterialTheme.shapes.medium // Bordes redondeados
    ) {
        Text(
            text = text,
            color = Color.White, // Texto blanco
            textAlign = TextAlign.Center
        )
    }
}

// Composable para centrar el título de la barra superior
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenteredTopAppBar(title: String) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
    )
}
