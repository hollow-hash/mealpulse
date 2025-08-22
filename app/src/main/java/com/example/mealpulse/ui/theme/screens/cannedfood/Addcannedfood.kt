package com.example.mealpulse.ui.theme.screens.cannedfood

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.mealpulse.R
import com.example.mealpulse.data.CannedfoodViewModel
import com.example.mealpulse.data.FooditemViewModel

@Composable
fun addCannedfoodScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var brand by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var unit by remember { mutableStateOf("") }
    var expirydate by remember { mutableStateOf("") }
    var purchasedate by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    val imageUri = rememberSaveable { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { imageUri.value = it }
    }

    val cannedfoodViewModel: CannedfoodViewModel = viewModel()
    val context = LocalContext.current

    val orangeGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFFFA726), // Light Orange
            Color(0xFFFF9800), // Primary Orange
            Color(0xFFF57C00)  // Dark Orange
        )
    )

    // Box with gradient background
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = orangeGradient)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val OrangePrimary = Color(0xFFFF9800)
            val OrangeDark = Color(0xFFF57C00)
            val OrangeLight = Color(0xFFFFCC80)}}

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                "Add Food Item",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )

            // 📷 Image Picker
            Card(
                shape = CircleShape,
                modifier = Modifier
                    .padding(10.dp)
                    .size(200.dp)
            ) {
                AsyncImage(
                    model = imageUri.value ?: R.drawable.add_b,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(200.dp)
                        .clickable { launcher.launch("image/*") }
                )
            }

            // 🏷️ Show Category (read-only, from nav args)
//            Text(
//                text = "",
//                fontSize = 18.sp,
//                fontWeight = FontWeight.SemiBold,
//                color = Color.DarkGray,
//                modifier = Modifier.padding(bottom = 10.dp)
//            )

            // ✍️ Editable fields
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Enter food item name", color = Color.White) },
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("Please enter food item name", color = Color.White) },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = Color.White) },
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            OutlinedTextField(
                value = brand,
                onValueChange = { brand = it },
                label = { Text("Enter food item type", color = Color.White) },
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("Please enter food item type", color = Color.White) },
                leadingIcon = { Icon(Icons.Default.Create, contentDescription = null, tint = Color.White) },
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            OutlinedTextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = { Text("Enter quantity", color = Color.White) },
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("Please enter quantity", color = Color.White) },
                leadingIcon = { Icon(Icons.Default.Menu, contentDescription = null, tint = Color.White) },
                modifier = Modifier.fillMaxWidth(0.8f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = unit,
                onValueChange = { unit = it },
                label = { Text("Enter unit", color = Color.White) },
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("Please enter unit", color = Color.White) },
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            OutlinedTextField(
                value = expirydate,
                onValueChange = { expirydate = it },
                label = { Text("Enter expiry date", color = Color.White) },
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("Please enter expiry date", color = Color.White) },
                leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = null, tint = Color.White) },
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            OutlinedTextField(
                value = purchasedate,
                onValueChange = { purchasedate = it },
                label = { Text("Enter purchase date", color = Color.White) },
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("Please enter purchase date", color = Color.White) },
                leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = null, tint = Color.White) },
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Enter location", color = Color.White) },
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("Please enter location", color = Color.White) },
                leadingIcon = { Icon(Icons.Default.Place, contentDescription = null, tint = Color.White) },
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // ✅ Upload button
            Button(
                onClick = {
                    cannedfoodViewModel.uploadCannedfood(
                        imageUri.value,
                        name,
                        brand,
                        quantity,
                        unit,
                        expirydate,
                        purchasedate,
                        location,
                        navController,
                        context
                    )
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Add Canned Food", color = Color(0xFFF57C00))
            }
        }
    }
}
