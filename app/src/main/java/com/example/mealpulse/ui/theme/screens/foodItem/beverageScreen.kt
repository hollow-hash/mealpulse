package com.example.mealpulse.ui.theme.screens.foodItem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mealpulse.R
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.mealpulse.data.FooditemViewModel
import com.example.mealpulse.models.FoodItem
import com.example.mealpulse.navigation.ROUTE_ADDFOODITEM


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun beverageScreen(navController: NavController) {
    val selectedItem = remember { mutableStateOf(null) }
    val fooditemViewModel: FooditemViewModel = viewModel()
    val fooditems = fooditemViewModel.fooditems
    val context = LocalContext.current

    val orangeGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFFFA726), // Light Orange
            Color(0xFFFF9800), // Primary Orange
            Color(0xFFF57C00)  // Dark Orange
        )
    )
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
            val OrangeLight = Color(0xFFFFCC80)

            Scaffold(bottomBar = {

                NavigationBar(containerColor = Color.Gray) {
                    NavigationBarItem(
                        selected = selectedItem.value == 0,
                        onClick = { navController.navigate(ROUTE_ADDFOODITEM) },
                        icon = { Icon(Icons.Filled.AddCircle, contentDescription = "Add") },
                        label = { Text(text = "Share") },
                        alwaysShowLabel = true,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Black,
                            unselectedIconColor = Color.Black

                        )
                    )
                }
            })
            { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {

                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Beverage",
                         fontWeight = FontWeight.Bold,
                        color = Color.LightGray
                            )





                    LaunchedEffect(Unit) {
                        fooditemViewModel.fetchfooditems(context)
                    }
                    LazyColumn {
                        items(fooditems) { fooditem ->
                            FooditemCard(
                                fooditem = fooditem,
                                onDelete = { fooditemId ->
                                    fooditemViewModel.deletefooditem(
                                        fooditemId,
                                        context,
                                    )
                                }, navController
                            )

                        }
                    }

                }
            }
        }
    }
}
    @Composable
    fun FooditemCard(fooditem: FoodItem,onDelete: (String) -> Unit,navController: NavController) {
        var showDialog by remember { mutableStateOf(false) }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = {showDialog = false},
                title = { Text("confirm delete") },
                text = { Text("Are you sure you want to delete this patient") },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog = false
                        fooditem.id?.let{onDelete(it)}
                    }) {
                        Text("yes", color = Color.Red)
                    }
                },
                dismissButton = {
                    TextButton(onClick = {showDialog = false}) {
                        Text("Cancel")
                    }
                }
            )
        }
        Card(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(Color.Gray)
        ) {
            Row(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
                fooditem.imageUrl?.let { imageUrl ->
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "Patient String",
                        modifier = Modifier.size(64.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = fooditem.name ?: "No name",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "QUANTITY:${fooditem.quantity}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "PURCHASE DATE:${fooditem.purchasedate}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "EXPIRY DATE:${fooditem.expirydate}",
                        style = MaterialTheme.typography.bodySmall
                    )

                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = {navController.navigate("update_fooditem/${fooditem.id}")}) {
                    Icon(Icons.Filled.Create, contentDescription = "update",tint = Color.Blue)}
                IconButton(onClick = {showDialog = true}) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete", tint = Color.Red)}
            }
        }
    }





@Preview(showBackground = true, showSystemUi = true)
@Composable
fun beverageScreenPreview(){
    beverageScreen(rememberNavController())
}






