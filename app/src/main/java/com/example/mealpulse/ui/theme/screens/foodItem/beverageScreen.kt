package com.example.mealpulse.ui.theme.screens.foodItem

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
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
import androidx.compose.ui.draw.clip
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
            Image(
                painter = painterResource(id = R.drawable.loginbg),
                contentDescription = "background image",
                contentScale = ContentScale.FillBounds
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = "Beverage",
                        fontWeight = FontWeight.Bold,
                        color = Color.LightGray
                    )
                },

                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.FavoriteBorder, contentDescription = "Favourite")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Unspecified,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
            LaunchedEffect(Unit) {
                fooditemViewModel.fetchfooditems(context)
            }
            LazyColumn {
                items(fooditems) { fooditem ->
                    FooditemCard(
                        fooditem = fooditem)
                }
            }

        }
    }
}
    @Composable
    fun FooditemCard(fooditem: FoodItem) {
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
                TextButton(onClick = { }) {
                    Text("Update", color = Color.Blue)
                }
                TextButton(onClick = {}) {
                    Text("Delete", color = Color.Red)
                }
            }
        }
    }





@Preview(showBackground = true, showSystemUi = true)
@Composable
fun beverageScreenPreview(){
    beverageScreen(rememberNavController())
}






