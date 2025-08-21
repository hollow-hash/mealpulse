package com.example.mealpulse.ui.theme.screens.foodItem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealpulse.data.FooditemViewModel
import com.example.mealpulse.models.FoodItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun beverageScreen(
    navController: NavController,
    foodItems: List<FoodItem>, // ✅ Passed from AppNavHost
    onDelete: (String) -> Unit, // ✅ Passed down so it calls ViewModel delete
    onAddFood: () -> Unit       // ✅ Add new beverage
) {
    val selectedItem = remember { mutableStateOf<Int?>(null) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFFF57C00),
                contentColor = Color(0xFFFAFAFA)
            ) {
                NavigationBarItem(
                    selected = selectedItem.value == 0,
                    onClick = { onAddFood() }, // ✅ use callback from AppNavHost
                    icon = {
                        Icon(
                            Icons.Filled.AddCircle,
                            contentDescription = "Add",
                            tint = Color(0xFFFAFAFA)
                        )
                    },
                    label = { Text(text = "Add Beverage") },
                    alwaysShowLabel = true,
                )
            }
        }
    ) { innerPadding ->
        val orangeGradient = Brush.verticalGradient(
            colors = listOf(
                Color(0xFFFFA726),
                Color(0xFFFF9800),
                Color(0xFFF57C00)
            )
        )

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(brush = orangeGradient)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Beverage",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 30.sp
                )

                LazyColumn {
                    if (foodItems.isEmpty()) {
                        item { Text("No beverages found") }
                    } else {
                        items(foodItems) { item ->
                            FooditemCard(
                                fooditem = item,
                                navController = navController,
                                onDelete = {
                                    item.id?.let { onDelete(it) } // ✅ trigger delete callback
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
