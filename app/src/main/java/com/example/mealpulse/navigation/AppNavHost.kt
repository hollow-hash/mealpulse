package com.example.mealpulse.navigation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mealpulse.data.FooditemViewModel
import com.example.mealpulse.ui.theme.screens.SplashScreen
import com.example.mealpulse.ui.theme.screens.dashboard.DashboardScreen
//import com.example.mealpulse.ui.theme.screens.foodItem.CannedfoodScreen
import com.example.mealpulse.ui.theme.screens.foodItem.addFooditemScreen
//import com.example.mealpulse.ui.theme.screens.foodItem.beverageScreen
import com.example.mealpulse.ui.theme.screens.foodItem.updateFooditemScreen
import com.example.mealpulse.ui.theme.screens.landing.landingScreen
import com.example.mealpulse.ui.theme.screens.login.loginScreen
import com.example.mealpulse.ui.theme.screens.register.registerScreen
import androidx.compose.runtime.collectAsState
import com.example.mealpulse.ui.theme.screens.cannedfood.addCannedfoodScreen
import com.example.mealpulse.ui.theme.screens.cannedfood.cannedfoodScreen
import com.example.mealpulse.ui.theme.screens.cannedfood.updatecannedfoodScreen
import com.example.mealpulse.ui.theme.screens.foodItem.beverageScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SPLASH,
) {
    // ✅ Reuse the same ViewModel for all screens
    val context = LocalContext.current
    val foodViewModel: FooditemViewModel = viewModel(context as ComponentActivity)

    NavHost(navController = navController, startDestination = startDestination) {
        composable(ROUTE_SPLASH) {
            SplashScreen {
                navController.navigate(ROUTE_REGISTER) {
                    popUpTo(ROUTE_SPLASH) { inclusive = true }
                }
            }
        }
        composable(ROUTE_LOGIN) { loginScreen(navController) }
        composable(ROUTE_LANDING) { landingScreen(navController) }
        composable(ROUTE_REGISTER) { registerScreen(navController) }
        composable(ROUTE_DASHBOARD) { DashboardScreen(navController) }
        composable(ROUTE_BEVERAGE) { beverageScreen(navController) }
        composable(ROUTE_ADDFOODITEM) { addFooditemScreen(navController) }

        composable(
            ROUTE_UPDATE_FOODITEM, arguments = listOf(navArgument("fooditemId")
        {type = NavType.StringType})) {
                backStackEntry ->
            val fooditemId = backStackEntry.arguments?.getString("fooditemId")!!
            updateFooditemScreen(navController,fooditemId)}
        composable(ROUTE_ADDCANNEDFOOD) { addCannedfoodScreen(navController) }
        composable(ROUTE_CANNED_FOOD) { cannedfoodScreen(navController) }
        composable(
            ROUTE_UPDATE_CANNEDFOOD, arguments = listOf(navArgument("cannedfoodId")
            {type = NavType.StringType})) {
                backStackEntry ->
            val cannedfoodId = backStackEntry.arguments?.getString("cannedfoodId")!!
            updatecannedfoodScreen(navController,cannedfoodId)}



    }}

        // ✅ Add food item (with category passed in route)
//        composable(
//            route = "$ROUTE_ADDFOODITEM/{category}",
//            arguments = listOf(navArgument("category") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val category = backStackEntry.arguments?.getString("category") ?: ""
//            addFooditemScreen(navController, category)
//        }
//
//        // ✅ Beverage screen
//        composable(
//            route = "$ROUTE_BEVERAGE/{category}",
//            arguments = listOf(navArgument("category") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val category = backStackEntry.arguments?.getString("category") ?: ""
//            LaunchedEffect(category) {
//                foodViewModel.fetchfooditems(context, category)
//            }
//            val foodItems by foodViewModel.fooditems.collectAsState(initial = emptyList())
//            beverageScreen(
//                navController, foodItems,
//                onDelete = TODO(),
//                onAddFood = TODO()
//            )
//        }
//

        // ✅ Canned food screen
//        composable(ROUTE_CANNED_FOOD) {
//            LaunchedEffect(ROUTE_CANNED_FOOD) {
//                foodViewModel.fetchfooditems(context, "cannedfood")
//            }
//
//            val foodItems by foodViewModel.fooditems.collectAsState(initial = emptyList())
//            CannedfoodScreen(
//                navController, foodItems,
//                onDelete = TODO(),
//                onAddFood = TODO()
//            )
//        }
//
//        // ✅ Update food item screen
//        composable(
//            ROUTE_UPDATE_FOODITEM,
//            arguments = listOf(navArgument("fooditemId") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val fooditemId = backStackEntry.arguments?.getString("fooditemId")!!
//            updateFooditemScreen(navController, fooditemId)
//        }
//    }
//}
