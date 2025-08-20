package com.example.mealpulse.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mealpulse.ui.theme.screens.SplashScreen
import com.example.mealpulse.ui.theme.screens.dashboard.DashboardScreen
import com.example.mealpulse.ui.theme.screens.foodItem.addFooditemScreen
import com.example.mealpulse.ui.theme.screens.foodItem.beverageScreen
import com.example.mealpulse.ui.theme.screens.foodItem.updateFooditemScreen
import com.example.mealpulse.ui.theme.screens.landing.landingScreen
import com.example.mealpulse.ui.theme.screens.login.loginScreen
import com.example.mealpulse.ui.theme.screens.register.registerScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(), startDestination:String= ROUTE_DASHBOARD ,){
    NavHost(navController=navController,startDestination = startDestination){
        composable(ROUTE_SPLASH) { SplashScreen { navController.navigate(ROUTE_LOGIN)
        {popUpTo(ROUTE_SPLASH){inclusive=true} } } }
        composable(ROUTE_LOGIN) { loginScreen(navController) }
        composable(ROUTE_LANDING) { landingScreen(navController) }
        composable(ROUTE_REGISTER) { registerScreen(navController) }
        composable(ROUTE_DASHBOARD) { DashboardScreen(navController) }
        composable(ROUTE_ADDFOODITEM){ addFooditemScreen(navController) }
        composable(ROUTE_BEVERAGE){ beverageScreen(navController) }
        composable(
            ROUTE_UPDATE_FOODITEM, arguments = listOf(
            navArgument("fooditemId")
        {type = NavType.StringType})) {
                backStackEntry ->
            val fooditemId = backStackEntry.arguments?.getString("fooditemId")!!
            updateFooditemScreen(navController,fooditemId)
        }


    }
}


