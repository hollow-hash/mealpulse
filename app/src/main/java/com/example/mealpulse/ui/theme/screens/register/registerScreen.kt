package com.example.mealpulse.ui.theme.screens.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mealpulse.data.AuthViewModel

@Composable
fun registerScreen(navController: NavController){
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phonenumber by remember { mutableStateOf("") }
    var nationality by remember { mutableStateOf("") }
    var household by remember { mutableStateOf("") }
    var authViewModel: AuthViewModel = viewModel()


    Box() {
//        Image(
//            painter = painterResource(id = R.drawable.loginbg),
//            contentDescription = "login bglog",
//            contentScale = ContentScale.FillBounds,
//            modifier = Modifier.fillMaxHeight()
//        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Create an account",
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
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(" Username", color = Color.Black) },
            textStyle = TextStyle(color = Color.Black),
            placeholder = { Text("Please enter Username", color = Color.Black) },
            leadingIcon = {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Lock icon",
                    tint = Color.Black
                )
            },
            modifier = Modifier.fillMaxWidth(0.8f).shadow(20.dp),
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(" Email", color = Color.Black) },
            textStyle = TextStyle(color = Color.Black),
            placeholder = { Text("Please enter email", color = Color.Black) },
            leadingIcon = {
                Icon(
                    Icons.Default.Email,
                    contentDescription = "Email icon",
                    tint = Color.Black
                )
            },
            modifier = Modifier.fillMaxWidth(0.8f).shadow(20.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password", color = Color.Black) },
            textStyle = TextStyle(color = Color.Black),
            placeholder = { Text("Please enter password", color = Color.Black) },
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(
                    Icons.Default.Lock,
                    contentDescription = "Lock icon",
                    tint = Color.Black
                )
            },
            modifier = Modifier.fillMaxWidth(0.8f).shadow(20.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = phonenumber,
            onValueChange = { phonenumber = it },
            label = { Text("Phone Number",color = Color.Black) },
            textStyle = TextStyle(color = Color.Black),
            placeholder = { Text("Please Enter phone number",color = Color.Black) },
            leadingIcon = {
                Icon(Icons.Default.Phone, contentDescription = "Phone icon", tint = Color.Black) },
            modifier = Modifier.fillMaxWidth(0.8f).shadow(20.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = nationality,
            onValueChange = { nationality = it },
            label = { Text("Nationality",color = Color.Black) },
            textStyle = TextStyle(color = Color.Black),
            placeholder = { Text("Please Enter Nationality",color = Color.Black) },
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "Phone icon", tint = Color.Black) },
            modifier = Modifier.fillMaxWidth(0.8f).shadow(20.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = household,
            onValueChange = { household = it },
            label = { Text("House Hold name",color = Color.Black) },
            textStyle = TextStyle(color = Color.Black),
            placeholder = { Text("Please Enter Household Name",color = Color.Black) },
            leadingIcon = {
                Icon(Icons.Default.LocationOn, contentDescription = "Phone icon", tint = Color.Black) },
            modifier = Modifier.fillMaxWidth(0.8f).shadow(20.dp)
        )
        Spacer(modifier = Modifier.height(50.dp))
        val context = LocalContext.current
        Button(onClick = {
            authViewModel.register(
                username = username,
                email = email,
                password = password,
                phonenumber= phonenumber,
                nationality = nationality,
                household = household,
                navController = navController,
                context = context
            )
        },
            modifier = Modifier
                .fillMaxWidth(0.8f)) {
            Text("Create Account")
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun registerScreenPreview(){
    registerScreen(rememberNavController())
}
