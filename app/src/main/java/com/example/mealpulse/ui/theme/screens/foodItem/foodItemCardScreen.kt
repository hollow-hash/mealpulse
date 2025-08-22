package com.example.mealpulse.ui.theme.screens.foodItem//package com.example.mealpulse.ui.theme.screens.foodItem
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Create
//import androidx.compose.material.icons.filled.Delete
//import androidx.compose.material3.AlertDialog
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import coil.compose.AsyncImage
//import com.example.mealpulse.models.FoodItem
//
//@Composable
//fun FooditemCard(fooditem: FoodItem, onDelete: (String) -> Unit, navController: NavController) {
//    var showDialog by remember { mutableStateOf(false) }
//
//    if (showDialog) {
//        AlertDialog(
//            onDismissRequest = {showDialog = false},
//            title = { Text("confirm delete") },
//            text = { Text("Are you sure you want to delete this patient") },
//            confirmButton = {
//                TextButton(onClick = {
//                    showDialog = false
//                    fooditem.id?.let{onDelete(it)}
//                }) {
//                    Text("yes", color = Color.Red)
//                }
//            },
//            dismissButton = {
//                TextButton(onClick = {showDialog = false}) {
//                    Text("Cancel")
//                }
//            }
//        )
//    }
//    Card(
//        modifier = Modifier.fillMaxWidth().padding(12.dp).clip(RoundedCornerShape(16.dp)),
//        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
//        colors = CardDefaults.cardColors(
//            Color(0xFFFAFAFA),
//        )
//    ) {
//        Row(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
//            fooditem.imageUrl?.let { imageUrl ->
//                AsyncImage(
//                    model = imageUrl,
//                    contentDescription = "Food Image",
//                    modifier = Modifier.size(64.dp).clip(CircleShape),
//                    contentScale = ContentScale.Crop
//                )
//            }
//            Spacer(modifier = Modifier.width(12.dp))
//            Column {
//                Text(
//                    text = fooditem.name ?: "No name",
//                    style = MaterialTheme.typography.titleMedium,
//                    color = Color(0xFF333333)
//
//                )
//                Text(
//                    text = "QUANTITY:${fooditem.quantity}",
//                    style = MaterialTheme.typography.bodySmall,
//                    color = Color(0xFF333333)
//
//                )
//                Text(
//                    text = "PURCHASE DATE:${fooditem.purchasedate}",
//                    style = MaterialTheme.typography.bodySmall,
//                    color = Color(0xFF333333)
//
//                )
//                Text(
//                    text = "EXPIRY DATE:${fooditem.expirydate}",
//                    style = MaterialTheme.typography.bodySmall,
//                    color = Color(0xFFF57C00)
//
//                )
//
//            }
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Row(
//            horizontalArrangement = Arrangement.End,
//            modifier = Modifier.fillMaxWidth().padding(end = 8.dp)
//        ) {
//            IconButton(onClick = {navController.navigate("update_fooditem/${fooditem.id}")}) {
//                Icon(Icons.Filled.Create, contentDescription = "update",tint = Color(0xFFFFA726))
//            }
//            IconButton(onClick = {showDialog = true}) {
//                Icon(Icons.Filled.Delete, contentDescription = "Delete", tint = Color.Red)
//            }
//        }
//    }
//}