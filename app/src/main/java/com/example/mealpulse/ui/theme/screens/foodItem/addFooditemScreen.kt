package com.example.mealpulse.ui.theme.screens.foodItem

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import com.example.mealpulse.data.fooditemViewModel


@Composable
fun addFooditemScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var unit by remember { mutableStateOf("") }
    var expirydate by remember { mutableStateOf("") }
    var purchasedate by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    val imageUri = rememberSaveable() { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
            uri: Uri? ->uri?.let { imageUri.value=it } }
    val fooditemViewModel: fooditemViewModel = viewModel()
    val context= LocalContext.current

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

    }
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text("Add Food Item",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Cursive,
                    fontStyle = FontStyle.Normal,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                )
                Card (
                    shape = CircleShape,
                    modifier = Modifier.padding(10.dp).size(200.dp)){
                    AsyncImage(
                        model = imageUri.value ?: R.drawable.add_b,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(20.dp).clickable{ launcher.launch("image/*")})}

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Enter food item name ") },
                    textStyle = TextStyle(color = Color.White),
                    placeholder = { Text("Please Enter food itam name") },
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                OutlinedTextField(
                    value = type,
                    onValueChange = { type = it },
                    label = { Text("Enter food item type ") },
                    textStyle = TextStyle(color = Color.White),
                    placeholder = { Text("Please Enter food item type") },
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                OutlinedTextField(
                    value = category,
                    onValueChange = { category = it },
                    label = { Text("Enter food item category") },
                    textStyle = TextStyle(color = Color.White),
                    placeholder = { Text("Please Enter food item category") },
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                OutlinedTextField(
                    value = quantity,
                    onValueChange = { quantity = it },
                    label = { Text("Enter quantity") },
                    textStyle = TextStyle(color = Color.White),
                    placeholder = { Text("Please Enter food item quantity") },
                    modifier = Modifier.fillMaxWidth(0.8f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
                OutlinedTextField(
                    value = unit,
                    onValueChange = { unit = it },
                    label = { Text("Enter Unit") },
                    textStyle = TextStyle(color = Color.White),
                    placeholder = { Text("Please Enter food item Unit") },
                    modifier = Modifier.fillMaxWidth(0.8f),

                )
                OutlinedTextField(
                    value = expirydate,
                    onValueChange = { expirydate = it },
                    label = { Text("Enter Expiry Date") },
                    textStyle = TextStyle(color = Color.White),
                    placeholder = { Text("Please Enter food item Expiry Date") },
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                OutlinedTextField(
                    value = purchasedate,
                    onValueChange = { purchasedate = it },
                    label = { Text("Enter Purchase Date") },
                    textStyle = TextStyle(color = Color.White),
                    placeholder = { Text("Please Enter food item Purchase Date") },
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Enter Description") },
                    textStyle = TextStyle(color = Color.White),
                    placeholder = { Text("Please Enter food item Description") },
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                OutlinedTextField(
                    value = location,
                    onValueChange = { location = it },
                    label = { Text("Enter Location") },
                    textStyle = TextStyle(color = Color.White),
                    placeholder = { Text("Please Enter food item location") },
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {
                    fooditemViewModel.uploadFooditem(
                        imageUri.value,
                        name,
                        type,
                        category,
                        quantity,
                        unit,
                        expirydate,
                        purchasedate,
                        description,
                        location,
                        navController,
                        context
                    )
                }) {
                    Text("Add Food Item")}

            }
        }

    }

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun addFooditemScreenPreview(){
    addFooditemScreen(rememberNavController())
}