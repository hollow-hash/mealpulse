package com.example.mealpulse.ui.theme.screens.cannedfood

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.mealpulse.data.CannedfoodViewModel
import com.example.mealpulse.models.CannedFood
import com.example.mealpulse.navigation.ROUTE_ADDFOODITEM

@Composable
fun cannedfoodScreen(navController: NavController) {
    val cannedfoodViewModel: CannedfoodViewModel = viewModel()
    val cannedfoods = cannedfoodViewModel.cannedfoods
    val context = LocalContext.current
    val selectedItem = remember { mutableStateOf<Int?>(null) }


    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFFF57C00),
                contentColor = Color(0xFFFAFAFA)
            ) {
                NavigationBarItem(
                    selected = selectedItem.value == 0,
                    onClick = { navController.navigate(ROUTE_ADDFOODITEM) }, // ✅ use callback from AppNavHost
                    icon = {
                        Icon(
                            Icons.Filled.AddCircle,
                            contentDescription = "Add",
                            tint = Color(0xFFFAFAFA)
                        )
                    },
                    label = { Text(text = "Add Canned food") },
                    alwaysShowLabel = true,
                )
            }
        }
    ) { innerPadding ->
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
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
//            val OrangePrimary = Color(0xFFFF9800)
//            val OrangeDark = Color(0xFFF57C00)
//            val OrangeLight = Color(0xFFFFCC80)

                Text(
                    "Canned Food",
                    fontSize = 27.sp,
                    fontFamily = FontFamily.Cursive,
                    fontStyle = FontStyle.Normal,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
                LaunchedEffect(Unit) {
                    cannedfoodViewModel.fetchcannedfood(context)
                }
                LazyColumn {
                    items(cannedfoods) { cannedfood ->
                        CannedfoodCard(
                           cannedfood = cannedfood,
                            onDelete = { cannedfoodId ->
                                cannedfoodViewModel.deletecannedfood(
                                    cannedfoodId,
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

@Composable
fun CannedfoodCard(cannedfood: CannedFood, onDelete: (String) -> Unit, navController: NavController){
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {showDialog = false},
            title = { Text("confirm delete") },
            text = { Text("Are you sure you want to delete this cannedfood") },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    cannedfood.id?.let{onDelete(it)}
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

    Card( modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(Color(0xFFFAFAFA))) {
        Row( modifier = Modifier.fillMaxWidth().padding(10.dp)){
            cannedfood.imageUrl?.let{imageUrl ->
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
                    text = cannedfood.name ?: "No name",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF333333)
                )

                Text(
                    text = "BRAND:${cannedfood.brand}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF333333)
                )

                Text(
                    text = "PURCHASE DATE:${cannedfood.purchasedate}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF333333)
                )

                Text(
                    text = "EXPIRY DATE:${cannedfood.expirydate}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFF57C00)
                )


            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row (horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()){
            Icon(
                Icons.Default.Create,
                contentDescription = "update",
                tint = Color(0xFFF57C00),
                modifier = Modifier
                    .size(30.dp)
                    .clickable { navController.navigate("update_cannedfood/${cannedfood.id}") }
            )
            Icon(
                Icons.Default.Delete,
                contentDescription = "delete",
                tint = Color.Red,
                modifier = Modifier
                    .size(30.dp)
                    .clickable { showDialog = true}
            )


        }
    }

}






//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun beverageScreen(
//    navController: NavController,
//    foodItems: List<FoodItem>, // ✅ Passed from AppNavHost
//    onDelete: (String) -> Unit, // ✅ Passed down so it calls ViewModel delete
//    onAddFood: () -> Unit       // ✅ Add new beverage
//) {
//    val selectedItem = remember { mutableStateOf<Int?>(null) }
//
//    Scaffold(
//        bottomBar = {
//            NavigationBar(
//                containerColor = Color(0xFFF57C00),
//                contentColor = Color(0xFFFAFAFA)
//            ) {
//                NavigationBarItem(
//                    selected = selectedItem.value == 0,
//                    onClick = { onAddFood() }, // ✅ use callback from AppNavHost
//                    icon = {
//                        Icon(
//                            Icons.Filled.AddCircle,
//                            contentDescription = "Add",
//                            tint = Color(0xFFFAFAFA)
//                        )
//                    },
//                    label = { Text(text = "Add Beverage") },
//                    alwaysShowLabel = true,
//                )
//            }
//        }
//    ) { innerPadding ->
//        val orangeGradient = Brush.verticalGradient(
//            colors = listOf(
//                Color(0xFFFFA726),
//                Color(0xFFFF9800),
//                Color(0xFFF57C00)
//            )
//        )
//
//        Box(
//            modifier = Modifier
//                .padding(innerPadding)
//                .fillMaxSize()
//                .background(brush = orangeGradient)
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(vertical = 16.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = "Beverage",
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Black,
//                    fontSize = 30.sp
//                )
//
//                LazyColumn {
//                    if (foodItems.isEmpty()) {
//                        item { Text("No beverages found") }
//                    } else {
//                        items(foodItems) { item ->
//                            FooditemCard(
//                                fooditem = item,
//                                navController = navController,
//                                onDelete = {
//                                    item.id?.let { onDelete(it) } // ✅ trigger delete callback
//                                }
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
