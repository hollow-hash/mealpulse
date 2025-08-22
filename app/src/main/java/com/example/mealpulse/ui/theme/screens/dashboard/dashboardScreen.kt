package com.example.mealpulse.ui.theme.screens.dashboard


import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.mealpulse.R
import com.example.mealpulse.navigation.ROUTE_ADDFOODITEM
//import com.example.mealpulse.navigation.ROUTE_ADDFOODITEM
import com.example.mealpulse.navigation.ROUTE_BEVERAGE
import com.example.mealpulse.navigation.ROUTE_CANNED_FOOD
import com.example.mealpulse.navigation.ROUTE_REGISTER
//import com.example.mealpulse.navigation.ROUTE_CANNED_FOOD


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController) {
    val selectedItem = remember { mutableStateOf(null) }

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
            val OrangeLight = Color(0xFFFFCC80)


            TopAppBar(
                title = {
                    Text(
                        text = "Meal Pulse",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },

                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Person, contentDescription = "Person")
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.AccountCircle, contentDescription = "Logout")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFF9800),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White)
                )

            LazyColumn {
                item {
                    Column {
                        Row(
                            modifier = Modifier.wrapContentWidth(),
                        ) {
                            Card(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clickable { }
                                    .size(150.dp),
                                shape = RoundedCornerShape(10.dp),
                                elevation = CardDefaults.cardElevation(50.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFA726)),
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                ) {
                                    Box(

                                        modifier = Modifier
                                            .height(110.dp).width(150.dp),
                                        contentAlignment = Alignment.Center,

                                        ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.pastries),
                                            contentDescription = "login bglog",
                                            contentScale = ContentScale.FillBounds,
                                            modifier = Modifier
                                                .size(140.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                                .fillMaxSize()
                                        )

                                    }
                                    Text(
                                        text = "Pastries",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Default,
                                        fontStyle = FontStyle.Normal,
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                    )
                                }

                            }

                            Spacer(modifier = Modifier.width(10.dp))
                            Card(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clickable { }
                                    .size(150.dp),
                                shape = RoundedCornerShape(10.dp),
                                elevation = CardDefaults.cardElevation(50.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFA726)),
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                ) {
                                    Box(

                                        modifier = Modifier
                                            .height(110.dp).width(150.dp),
                                        contentAlignment = Alignment.Center,

                                        ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.dairy),
                                            contentDescription = "login bglog",
                                            contentScale = ContentScale.FillBounds,
                                            modifier = Modifier
                                                .size(140.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                                .fillMaxSize()
                                        )

                                    }
                                    Text(
                                        text = "Dairy",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Default,
                                        fontStyle = FontStyle.Normal,
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                    )
                                }

                            }
                        }
                        Row(
                            modifier = Modifier.wrapContentWidth(),
                        ) {
                            Card(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clickable { }
                                    .size(150.dp),
                                shape = RoundedCornerShape(10.dp),
                                elevation = CardDefaults.cardElevation(50.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFA726)),
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                ) {
                                    Box(

                                        modifier = Modifier
                                            .height(110.dp).width(150.dp),
                                        contentAlignment = Alignment.Center,

                                        ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.snacks),
                                            contentDescription = "login bglog",
                                            contentScale = ContentScale.FillBounds,
                                            modifier = Modifier
                                                .size(140.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                                .fillMaxSize()
                                        )

                                    }
                                    Text(
                                        text = "Snacks",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Default,
                                        fontStyle = FontStyle.Normal,
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                    )
                                }

                            }

                            Spacer(modifier = Modifier.width(10.dp))
                            Card(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clickable { navController.navigate(ROUTE_BEVERAGE) }
                                    .size(150.dp),
                                shape = RoundedCornerShape(10.dp),
                                elevation = CardDefaults.cardElevation(50.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFA726)),
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                ) {
                                    Box(

                                        modifier = Modifier
                                            .height(110.dp).width(150.dp),
                                        contentAlignment = Alignment.Center,

                                        ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.beverage),
                                            contentDescription = "login bglog",
                                            contentScale = ContentScale.FillBounds,
                                            modifier = Modifier
                                                .size(140.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                                .fillMaxSize()
                                        )

                                    }
                                    Text(
                                        text = "Beverage",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Default,
                                        fontStyle = FontStyle.Normal,
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                    )
                                }

                            }
                        }
                        Row(
                            modifier = Modifier.wrapContentWidth(),
                        ) {
                            Card(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clickable { }
                                    .size(150.dp),
                                shape = RoundedCornerShape(10.dp),
                                elevation = CardDefaults.cardElevation(50.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFA726)),
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                ) {
                                    Box(

                                        modifier = Modifier
                                            .height(110.dp).width(150.dp),
                                        contentAlignment = Alignment.Center,

                                        ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.vegatables),
                                            contentDescription = "login bglog",
                                            contentScale = ContentScale.FillBounds,
                                            modifier = Modifier
                                                .size(140.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                                .fillMaxSize()
                                        )

                                    }
                                    Text(
                                        text = "vegetables",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Default,
                                        fontStyle = FontStyle.Normal,
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                    )
                                }

                            }

                            Spacer(modifier = Modifier.width(10.dp))
                            Card(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clickable { }
                                    .size(150.dp),
                                shape = RoundedCornerShape(10.dp),
                                elevation = CardDefaults.cardElevation(50.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFA726)),
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                ) {
                                    Box(

                                        modifier = Modifier
                                            .height(110.dp).width(150.dp),
                                        contentAlignment = Alignment.Center,

                                        ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.fruits),
                                            contentDescription = "login bglog",
                                            contentScale = ContentScale.FillBounds,
                                            modifier = Modifier
                                                .size(140.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                                .fillMaxSize()
                                        )

                                    }
                                    Text(
                                        text = "Fruits",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Default,
                                        fontStyle = FontStyle.Normal,
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                    )
                                }

                            }
                        }
                        Row(
                            modifier = Modifier.wrapContentWidth(),
                        ) {
                            Card(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clickable { }
                                    .size(150.dp),
                                shape = RoundedCornerShape(10.dp),
                                elevation = CardDefaults.cardElevation(50.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFA726)),
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                ) {
                                    Box(

                                        modifier = Modifier
                                            .height(110.dp).width(150.dp),
                                        contentAlignment = Alignment.Center,

                                        ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.grains),
                                            contentDescription = "login bglog",
                                            contentScale = ContentScale.FillBounds,
                                            modifier = Modifier
                                                .size(140.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                                .fillMaxSize()
                                        )

                                    }
                                    Text(
                                        text = "Grains",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Default,
                                        fontStyle = FontStyle.Normal,
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                    )
                                }

                            }

                            Spacer(modifier = Modifier.width(10.dp))
                            Card(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clickable { }
                                    .size(150.dp),
                                shape = RoundedCornerShape(10.dp),
                                elevation = CardDefaults.cardElevation(50.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFA726)),
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                ) {
                                    Box(

                                        modifier = Modifier
                                            .height(110.dp).width(150.dp),
                                        contentAlignment = Alignment.Center,

                                        ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.seafood),
                                            contentDescription = "login bglog",
                                            contentScale = ContentScale.FillBounds,
                                            modifier = Modifier
                                                .size(140.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                                .fillMaxSize()
                                        )

                                    }
                                    Text(
                                        text = "Sea Food",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Default,
                                        fontStyle = FontStyle.Normal,
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                    )
                                }

                            }
                        }
                        Row(
                            modifier = Modifier.wrapContentWidth(),
                        ) {
                            Card(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clickable { navController.navigate(ROUTE_CANNED_FOOD)}
                                    .size(150.dp),
                                shape = RoundedCornerShape(10.dp),
                                elevation = CardDefaults.cardElevation(50.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFA726)),
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                ) {
                                    Box(

                                        modifier = Modifier
                                            .height(110.dp).width(150.dp),
                                        contentAlignment = Alignment.Center,

                                        ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.canned),
                                            contentDescription = "login bglog",
                                            contentScale = ContentScale.FillBounds,
                                            modifier = Modifier
                                                .size(140.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                                .fillMaxSize()
                                        )

                                    }
                                    Text(
                                        text = "Canned food",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Default,
                                        fontStyle = FontStyle.Normal,
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                    )
                                }

                            }

                            Spacer(modifier = Modifier.width(10.dp))
                            Card(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clickable { }
                                    .size(150.dp),
                                shape = RoundedCornerShape(10.dp),
                                elevation = CardDefaults.cardElevation(50.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFA726)),
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                ) {
                                    Box(

                                        modifier = Modifier
                                            .height(110.dp).width(150.dp),
                                        contentAlignment = Alignment.Center,

                                        ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.frozen),
                                            contentDescription = "login bglog",
                                            contentScale = ContentScale.FillBounds,
                                            modifier = Modifier
                                                .size(140.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                                .fillMaxSize()
                                        )

                                    }
                                    Text(
                                        text = "Pastries",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Default,
                                        fontStyle = FontStyle.Normal,
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                    )
                                }

                            }
                        }
                        Row(
                            modifier = Modifier.wrapContentWidth(),
                        ) {
                            Card(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clickable { }
                                    .size(150.dp),
                                shape = RoundedCornerShape(10.dp),
                                elevation = CardDefaults.cardElevation(50.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFA726)),
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                ) {
                                    Box(

                                        modifier = Modifier
                                            .height(110.dp).width(150.dp),
                                        contentAlignment = Alignment.Center,

                                        ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.meat),
                                            contentDescription = "login bglog",
                                            contentScale = ContentScale.FillBounds,
                                            modifier = Modifier
                                                .size(140.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                                .fillMaxSize()
                                        )

                                    }
                                    Text(
                                        text = "Meat",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Default,
                                        fontStyle = FontStyle.Normal,
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                    )
                                }

                            }


                        }

                    }
                }
            }
//            Spacer(modifier = Modifier.height(600.dp))
//            Row(
//                horizontalArrangement = Arrangement.End,
//            ) {
//                Card(
//                    shape = CircleShape,
//                    modifier = Modifier.size(60.dp)
//                ) {
//                    AsyncImage(
//                        model = R.drawable.add_b,
//                        contentDescription = null,
//                        contentScale = ContentScale.Crop,
//                        modifier = Modifier.size(200.dp).clickable {
//                            navController.navigate(
//                                ROUTE_ADDFOODITEM
//                            )
//                        }
//                    )
//
//
//                }
//            }

        }

    }
}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview(){
    DashboardScreen(rememberNavController())
}