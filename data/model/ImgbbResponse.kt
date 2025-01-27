package com.example.hospitalturnmanagement.data.model

import com.google.gson.annotations.SerializedName

data class ImgbbResponse(
    @SerializedName("data") val data: ImgbbData,
    @SerializedName("success") val success: Boolean,
    @SerializedName("status") val status: Int
)

data class ImgbbData(
    @SerializedName("display_url") val displayUrl: String, // Aquí se mapea el campo display_url
    @SerializedName("delete_url") val deleteUrl: String? = null // Opcional: URL para eliminar la imagen
)
