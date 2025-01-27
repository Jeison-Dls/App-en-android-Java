package com.example.hospitalturnmanagement




import ImageUploadScreen
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.hospitalturnmanagement.ui.login.LoginScreen
import com.example.hospitalturnmanagement.ui.login.LoginViewModel
import com.example.hospitalturnmanagement.ui.theme.HospitalTurnManagementTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.hospitalturnmanagement.ui.menu.MainMenuScreen
import com.example.hospitalturnmanagement.data.api.ApiClient
import com.example.hospitalturnmanagement.data.repository.ImageRepository
import com.example.hospitalturnmanagement.ui.doctors.DoctorsScreen
import com.example.hospitalturnmanagement.ui.images.ImageSearchScreen
import com.example.hospitalturnmanagement.ui.images.viewmodel.ImageUploadViewModel
import com.example.hospitalturnmanagement.ui.images.viewmodel.ImageUploadViewModelFactory
import com.example.hospitalturnmanagement.ui.login.LoginViewModelFactory
import com.example.hospitalturnmanagement.ui.main.MaintenanceMenuScreen
import com.example.hospitalturnmanagement.ui.patients.PatientsScreen
import com.example.hospitalturnmanagement.ui.turnos.TurnosScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HospitalTurnManagementTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            // Crea el ApiService (o pasa tu instancia de Retrofit)
            val apiService = ApiClient.apiService

            // Usa el LoginViewModelFactory
            val factory = LoginViewModelFactory(apiService)
            val loginViewModel: LoginViewModel = viewModel(factory = factory)

            LoginScreen(
                onLoginSuccess = { navController.navigate("main_menu") },
                viewModel = loginViewModel
            )
        }
        // Menú Principal
        composable("main_menu") {
            MainMenuScreen(
                onNavigateToMaintenance = { navController.navigate("maintenance_menu") },
                onNavigateToConsultations = { /* Implementar navegación a consultas */ },
                onNavigateToImageSearch = { navController.navigate("image_search") }, // Navegación a búsqueda de imágenes
                onNavigateToImageUpload = { navController.navigate("image_upload") }, // Navegación a carga de imágenes
                onLogout = { navController.navigate("login") }
            )
        }
        // Menú de Mantenimientos
        composable("maintenance_menu") {
            MaintenanceMenuScreen(
                onNavigateToPatientMaintenance = {
                    navController.navigate("patients") // Navegar al mantenimiento de pacientes
                },
                onNavigateToDoctorMaintenance = { navController.navigate("doctors") },
                onNavigateToTurnos = { navController.navigate("turnos") } // Nueva ruta para turnos
            )
        }
        // Mantenimiento de Pacientes
        composable("patients") {
            PatientsScreen()
        }
        composable("doctors") {
            DoctorsScreen()
        }
        // Gestión de Turnos
        composable("turnos") {
            TurnosScreen()
        }
        // Búsqueda de Imágenes
        composable("image_search") {
            ImageSearchScreen()
        }

        // Carga de Imágenes
        composable(route = "image_upload") {
            val repository = ImageRepository(
                unsplashApiService = ApiClient.unsplashApiService,
                imgbbApiService = ApiClient.imgbbApiService
            )
            val viewModel: ImageUploadViewModel = viewModel(factory = ImageUploadViewModelFactory(repository))
            ImageUploadScreen(viewModel = viewModel)
        }

    }
}
