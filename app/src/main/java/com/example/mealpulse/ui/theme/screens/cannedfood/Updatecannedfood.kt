package com.example.mealpulse.ui.theme.screens.cannedfood


import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.mealpulse.data.CannedfoodViewModel
import com.example.mealpulse.data.FooditemViewModel
import com.example.mealpulse.models.CannedFood
import com.example.mealpulse.models.FoodItem
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await


@Composable
fun updatecannedfoodScreen(navController: NavController,cannedfoodId:String) {
    val cannedfoodViewModel: CannedfoodViewModel = viewModel()
    var cannedfood by remember { mutableStateOf<CannedFood?>(null) }
    LaunchedEffect(cannedfoodId) {
        val ref = FirebaseDatabase.getInstance().getReference("Cannedfood").child(cannedfoodId)
        val snapshot = ref.get().await()
        cannedfood = snapshot.getValue(CannedFood::class.java)?.apply {
            id = cannedfoodId
        }
    }
    if (cannedfood == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }
    var name by remember { mutableStateOf(cannedfood!!.name ?:"") }
    var brand by remember { mutableStateOf(cannedfood!!.brand ?:"") }
    var quantity by remember { mutableStateOf(cannedfood!!.quantity ?:"") }
    var unit by remember { mutableStateOf(cannedfood!!.unit ?:"") }
    var expirydate by remember { mutableStateOf(cannedfood!!.expirydate ?:"") }
    var purchasedate by remember { mutableStateOf(cannedfood!!.purchasedate ?:"") }
    var location by remember { mutableStateOf(cannedfood!!.location ?:"") }
    val imageUri = remember() { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        it?.let { uri -> imageUri.value = uri } }

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
                    model = imageUri.value ?: cannedfood!!.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(200.dp).clickable{ launcher.launch("image/*")})
            }
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Enter canned food name ") },
                textStyle = TextStyle(color = Color.Black),
                placeholder = { Text("Please Enter Canned food name") },
                leadingIcon = {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "Person",
                        tint = Color.Black
                    )
                },
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            OutlinedTextField(
                value = brand,
                onValueChange = { brand = it },
                label = { Text("Enter canned food type ") },
                textStyle = TextStyle(color = Color.Black),
                placeholder = { Text("Please Enter canned food type") },
                leadingIcon = {
                    Icon(
                        Icons.Default.Create,
                        contentDescription = "Brand",
                        tint = Color.Black
                    )
                },
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            OutlinedTextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = { Text("Enter quantity") },
                textStyle = TextStyle(color = Color.Black),
                placeholder = { Text("Please Enter canned food quantity") },
                leadingIcon = {
                    Icon(
                        Icons.Default.Menu,
                        contentDescription = "Email icon",
                        tint = Color.Black
                    )
                },
                modifier = Modifier.fillMaxWidth(0.8f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
            OutlinedTextField(
                value = unit,
                onValueChange = { unit = it },
                label = { Text("Enter Unit") },
                textStyle = TextStyle(color = Color.Black),
                placeholder = { Text("Please Enter canned food Unit") },
                modifier = Modifier.fillMaxWidth(0.8f),

                )
            OutlinedTextField(
                value = expirydate,
                onValueChange = { expirydate = it },
                label = { Text("Enter Expiry Date") },
                textStyle = TextStyle(color = Color.Black),
                placeholder = { Text("Please Enter canned food Expiry Date") },
                leadingIcon = {
                    Icon(
                        Icons.Default.DateRange,
                        contentDescription = "Email icon",
                        tint = Color.Black
                    )
                },
                modifier = Modifier.fillMaxWidth(0.8f)

            )
            OutlinedTextField(
                value = purchasedate,
                onValueChange = { purchasedate = it },
                label = { Text("Enter Purchase Date") },
                textStyle = TextStyle(color = Color.Black),
                placeholder = { Text("Please Enter canned food Purchase Date") },
                leadingIcon = {
                    Icon(
                        Icons.Default.DateRange,
                        contentDescription = "Email icon",
                        tint = Color.Black
                    )
                },
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Enter Location") },
                textStyle = TextStyle(color = Color.Black),
                placeholder = { Text("Please Enter canned food location") },
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                cannedfoodViewModel.updatecannedfood(
                    cannedfoodId,
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
            }) {
                Text("Update Food Item")}

        }
    }

}


