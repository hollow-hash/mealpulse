package com.example.mealpulse.data

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.mealpulse.models.CannedFood
import com.example.mealpulse.models.FoodItem
import com.example.mealpulse.navigation.ROUTE_BEVERAGE
import com.example.mealpulse.navigation.ROUTE_CANNED_FOOD
import com.example.mealpulse.navigation.ROUTE_DASHBOARD
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

class CannedfoodViewModel: ViewModel() {
    val cloudinaryUrl = "https://api.cloudinary.com/v1_1/diovcqdhx/image/upload"
    val uploadPreset = "meatpulse_images"

    fun uploadCannedfood(
        imageUri: Uri?,
        name: String,
        brand: String,
        quantity: String,
        unit: String,
        expirydate: String,
        purchasedate: String,
        location: String,
        navController: NavController,
        context: Context
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val imageUrl = imageUri?.let { uploadToCloudinary(context, it) }
                val ref = FirebaseDatabase.getInstance().getReference("Cannedfood").push()
                val foodItemData = mapOf(
                    "id" to ref.key,
                    "name" to name,
                    "brand" to brand,
                    "quantity" to quantity,
                    "unit" to unit,
                    "expirydate" to expirydate,
                    "purchasedate" to purchasedate,
                    "location" to location,
                    "imageUrl" to imageUrl
                )
                ref.setValue(foodItemData).await()
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Canned food saved Successfully", Toast.LENGTH_LONG).show()
                    navController.navigate(ROUTE_CANNED_FOOD) { popUpTo(0) }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Food Item not saved", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    private fun uploadToCloudinary(context: Context, uri: Uri): String {
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
    private val _cannedfood = mutableStateListOf<CannedFood>()
    val cannedfoods:List<CannedFood> = _cannedfood

    fun fetchcannedfood(context: Context) {
        val ref = FirebaseDatabase.getInstance().getReference("Cannedfood")
        ref.get().addOnSuccessListener { snapshot ->
            _cannedfood.clear()
            for (child in snapshot.children){
                val cannedfood = child.getValue(CannedFood::class.java)
                cannedfood?.let {_cannedfood.add(it)}
            }
        }.addOnFailureListener{
            Toast.makeText(context,"Failed to load canned food",Toast.LENGTH_LONG).show()

        }

    }
    fun updatecannedfood(
        cannedfoodId: String,
        imageUri: Uri?,
        name: String,
        brand: String,
        quantity: String,
        unit: String,
        expirydate: String,
        purchasedate: String,
        location: String,
        navController: NavController,
        context: Context
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val imageUrl = imageUri?.let { uploadToCloudinary(context, it) }
                val updatecannedfood = mapOf(
                    "id" to cannedfoodId,
                    "name" to name,
                    "brand" to brand,
                    "quantity" to quantity,
                    "unit" to unit,
                    "expirydate" to expirydate,
                    "purchasedate" to purchasedate,
                    "location" to location,
                    "imageUrl" to imageUrl
                )
                val ref = FirebaseDatabase.getInstance().getReference("Cannedfood").child(cannedfoodId)
                ref.setValue(updatecannedfood).await()
                fetchcannedfood(context)
                withContext(Dispatchers.Main){
                    Toast.makeText(context,"Canned food uploaded successfully",Toast.LENGTH_LONG).show()
                    navController.navigate(ROUTE_CANNED_FOOD){popUpTo(0)}
                }
            }catch (e : Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(context,"Update failed",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    fun deletecannedfood(cannedfoodId: String, context: Context) {
        val ref = FirebaseDatabase.getInstance().getReference("Cannedfood").child(cannedfoodId)
        ref.removeValue().addOnSuccessListener {
            // ✅ Do NOT auto-update list here
            Toast.makeText(context, "Food item deleted", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(context, "Food item not deleted", Toast.LENGTH_LONG).show()
        }
    }
}