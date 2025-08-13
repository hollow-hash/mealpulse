package com.example.mealpulse.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mealpulse.ui.theme.screens.login.loginScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(), startDestination:String= ROUTE_LOGIN ,){
    NavHost(navController=navController,startDestination = startDestination){
        composable(ROUTE_LOGIN) { loginScreen(navController) }
    }
}


