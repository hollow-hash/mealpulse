package com.example.mealpulse.data

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.input.key.Key.Companion.Calendar
import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.mealpulse.R
import com.example.mealpulse.models.FoodItem
import com.example.mealpulse.navigation.ROUTE_BEVERAGE
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
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat




class FooditemViewModel: ViewModel() {
    val cloudinaryUrl = "https://api.cloudinary.com/v1_1/diovcqdhx/image/upload"
    val uploadPreset = "meatpulse_images"

    fun uploadFooditem(
        imageUri: Uri?,
        name: String,
        brand:String,
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
                val imageUrl = imageUri?.let { uploadToCLoudinary(context, it) }
                val ref = FirebaseDatabase.getInstance().getReference("FoodItem").push()
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
                    Toast.makeText(context, "Food Item saved Successfully", Toast.LENGTH_LONG)
                        .show()
                    navController.navigate(ROUTE_BEVERAGE) { popUpTo(0) }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Food Item not saved", Toast.LENGTH_LONG).show()

                }
            }
        }

    }

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
    private val _fooditem = mutableStateListOf<FoodItem>()
    val fooditems:List<FoodItem> = _fooditem

    fun fetchfooditems(context: Context) {
        val ref = FirebaseDatabase.getInstance().getReference("FoodItem")
        ref.get().addOnSuccessListener { snapshot ->
            _fooditem.clear()
            for (child in snapshot.children){
                val patient = child.getValue(FoodItem::class.java)
                patient?.let {_fooditem.add(it)}
            }
        }.addOnFailureListener{
            Toast.makeText(context,"failed to load fooditems",Toast.LENGTH_LONG).show()

        }

    }
}
    // MainActivity.kt


//    class MainActivity : AppCompatActivity() {
//
//        private lateinit var dateEditText: EditText
//
//        override fun onCreate(savedInstanceState: Bundle?) {
//            super.onCreate(savedInstanceState)
//            setContentView(R.xml.activity_main)
//
//            dateEditText = findViewById(R.id.dateEditText)
//
//            dateEditText.setOnClickListener {
//                // Create the Material Date Picker
//                val datePicker = MaterialDatePicker.Builder.datePicker()
//                    .setTitleText("Select a date")
//                    .build()
//
//                // Show the picker
//                datePicker.show(supportFragmentManager, "MATERIAL_DATE_PICKER")
//
//                // Handle the positive button click
//                datePicker.addOnPositiveButtonClickListener { selection ->
//                    // Format and set the date
//                    val formattedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//                        .format(Date(selection))
//                    dateEditText.setText(formattedDate)
//                }
//            }
//        }
//
//        class Date(selection: Long?) {
//
//        }


