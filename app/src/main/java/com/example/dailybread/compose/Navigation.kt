package com.example.dailybread.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dailybread.screens.*

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = "router"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("router") {
            RouterScreen(navController)
        }
        composable("home") {
            HomeScreen(navController)
        }
        composable("signup") { SignUpScreen(navController) }
        composable("login") {
            LoginScreen(navController)
        }
        composable("inventory") {
            InventoryScreen(navController)
        }
        composable("edit") {
            EditInventoryScreen(navController)
        }
        composable("account") {
            AccountScreen(navController)
        }
        composable("recipe")
           {
            RecipeScreen(navController)
        }
        composable("recipeList") {
            RecipeListScreen(navController)
        }
        composable("Logout"){
            LoginScreen(navController)
        }
    }
}