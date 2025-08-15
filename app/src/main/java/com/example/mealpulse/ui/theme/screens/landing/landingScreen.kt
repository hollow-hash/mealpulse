package com.example.mealpulse.ui.theme.screens.landing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mealpulse.navigation.ROUTE_REGISTER

@Composable
fun landingScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
        Button(onClick = {navController.navigate(ROUTE_REGISTER)}) { Text("User",
            modifier = Modifier.fillMaxWidth(0.5f),
            textAlign = TextAlign.Center,) }
        Button(onClick = {}) { Text("Company",
            modifier = Modifier.fillMaxWidth(0.5f),
            textAlign = TextAlign.Center,) }
        Button(onClick = {}) { Text("Merchant",
            modifier = Modifier.fillMaxWidth(0.5f),
            textAlign = TextAlign.Center,) }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun landingScreenPreview(){
    landingScreen(rememberNavController())
}
