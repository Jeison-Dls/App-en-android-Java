package com.example.hospitalturnmanagement.utils

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

fun Context.uriToFile(uri: Uri): File {
    val inputStream: InputStream? = contentResolver.openInputStream(uri)
    val tempFile = File.createTempFile("temp_image", ".jpg", cacheDir)
    tempFile.deleteOnExit()
    inputStream?.use { input ->
        FileOutputStream(tempFile).use { output ->
            input.copyTo(output)
        }
    }
    return tempFile
}
