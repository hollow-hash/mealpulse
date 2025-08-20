package com.example.mealpulse.ui.theme.screens.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
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
import com.example.mealpulse.R
import com.example.mealpulse.navigation.ROUTE_REGISTER

@Composable
fun landingScreen(navController: NavController) {
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

            // Logo
            Card(
                shape = CircleShape,
                modifier = Modifier.size(100.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Image Logo",
                    modifier = Modifier
                        .fillMaxSize()
                        .size(500.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.FillBounds,
                    )}



                    Text(
                        "Register As A:",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        fontStyle = FontStyle.Normal,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(onClick = { navController.navigate(ROUTE_REGISTER) },
                        colors = ButtonDefaults.buttonColors(OrangeDark)
                    ) {
                        Text(
                            "User",
                            modifier = Modifier.fillMaxWidth(0.5f),
                            textAlign = TextAlign.Center,
                        )
                    }
                    Button(onClick = {},
                        colors = ButtonDefaults.buttonColors(OrangeDark)
                    ) {
                        Text(
                            "Company",
                            modifier = Modifier.fillMaxWidth(0.5f),
                            textAlign = TextAlign.Center,

                        )
                    }
                    Button(onClick = {},
                        colors = ButtonDefaults.buttonColors(OrangeDark)
                    ) {
                        Text(
                            "Merchant",
                            modifier = Modifier.fillMaxWidth(0.5f),
                            textAlign = TextAlign.Center,
                        )
                    }
                }

        }

    }


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun landingScreenPreview(){
    landingScreen(rememberNavController())
}
