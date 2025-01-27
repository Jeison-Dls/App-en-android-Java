import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.hospitalturnmanagement.ui.images.viewmodel.ImageUploadViewModel
import com.example.hospitalturnmanagement.utils.fileToBase64
import com.example.hospitalturnmanagement.utils.uriToFile
import java.io.File
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter


@Composable
fun ImageUploadScreen(viewModel: ImageUploadViewModel) {
    val context = LocalContext.current // Obtener el contexto


    // Estado para almacenar la URI de la imagen seleccionada
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    // Lanzador para abrir el selector de contenido
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        imageUri = uri // Guarda la URI seleccionada
    }

    // Estado para observar el resultado del proceso de subida de imágenes
    val uploadResult by viewModel.uploadResult.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Botón para seleccionar una imagen desde el almacenamiento
        Button(onClick = { launcher.launch("image/*") }) {
            Text("Seleccionar Imagen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Si se ha seleccionado una imagen, muestra el botón para subirla
        imageUri?.let { uri ->
            Button(onClick = {
                val file = context.uriToFile(uri) // Convertir Uri a File
                viewModel.uploadImage("8b14d10298ce569954d3defe13883e1e", file)
            }) {
                Text("Subir Imagen")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Si hay un resultado de la subida, lo muestra
        uploadResult?.let { url ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Resultado: $url")
                Spacer(modifier = Modifier.height(16.dp))
                // Muestra la imagen subida usando su URL
                Image(
                    painter = rememberAsyncImagePainter(url),
                    contentDescription = "Imagen subida",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(200.dp)
                )
                // Botón para copiar la URL al portapapeles
                Button(onClick = {
                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clip = ClipData.newPlainText("Imagen URL", url)
                    clipboard.setPrimaryClip(clip)
                    Toast.makeText(context, "Enlace copiado al portapapeles", Toast.LENGTH_SHORT).show()
                }) {
                    Text("Copiar Enlace")
                }
            }
        }
    }
}

/*paralelismo
* POST /upload: Procesa las imágenes subidas y devuelve una URL que las representa.*/
