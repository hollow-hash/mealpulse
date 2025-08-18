package com.example.mealpulse.data

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.InputStream

class fooditemViewModel:ViewModel() {
    val cloudinaryUrl = "https://api.cloudinary.com/v1_1/diovcqdhx/image/upload"
    val uploadPreset = "meatpulse_images"

    private fun uploadToCLoudinary(context: Context, uri: Uri): String {
        val contentResolver = context.contentResolver
        val inputStream: InputStream? = contentResolver.openInputStream(uri)
        val fileBytes = inputStream?.readBytes() ?: throw Exception("Image read failed")
        val requestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart(
                "file", "image.jpg",
                RequestBody.create("image/*".toMediaTypeOrNull(), fileBytes)
            )
            .addFormDataPart("upload_preset", uploadPreset).build()
        val request = Request.Builder().url(cloudinaryUrl).post(requestBody).build()
        val response = OkHttpClient().newCall(request).execute()
        if (!response.isSuccessful) throw Exception("Upload Failed")
        val responseBody = response.body?.string()
        val secureUrl = Regex("\"secure_url\":\"(.*?)\"")
            .find(responseBody ?: "")?.groupValues?.get(1)
        return secureUrl ?: throw Exception("Failed to get image url")

    }
    fun uploadFooditem(
        imageUri: Uri?,
        name: String,
        type: String,
        category: String,
        quantity: String,
        unit: String,
        expirydate: String,
        purchasedate: String,
        description: String,
        location: String,
        navController: NavController,
        context: Context
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val imageUrl = imageUri?.let { uploadToCLoudinary(context, it) }
                val ref = FirebaseDatabase.getInstance().getReference("FoodItem").push()
                val patientData = mapOf(
                    "id" to ref.key,
                    "name" to name,
                    "type" to type,
                    "category" to category,
                    "quantity" to quantity,
                    "unit" to unit,
                    "expirydate" to expirydate,
                    "purchasedate" to purchasedate,
                    "description" to description,
                    "location" to location,
                    "imageUrl" to imageUrl
                )
                ref.setValue(patientData).await()
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Food Item saved Successfully", Toast.LENGTH_LONG).show()
                }
            }catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Food Item not saved", Toast.LENGTH_LONG).show()
        }
    }
}


}
}