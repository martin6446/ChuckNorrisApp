package com.example.chucknorrisjokesapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.chucknorrisjokesapp.ui.categories.CategoriesScreen
import com.example.chucknorrisjokesapp.ui.categories.ChuckNorrisCategoriesViewModel
import com.example.chucknorrisjokesapp.ui.random_joke_item.ChuckNorrisRandomJokeViewModel
import com.example.chucknorrisjokesapp.ui.random_joke_item.RandomJokeScreen
import com.example.chucknorrisjokesapp.utils.Routes

@Composable
fun Navigation(navHostController: NavHostController, modifier: Modifier) {


    NavHost(
        navController = navHostController,
        startDestination = Routes.RANDOM_JOKE + "?category={category}",
        modifier = modifier
    ) {
        composable(Routes.RANDOM_JOKE + "?category={category}",
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) {
            RandomJokeScreen()
        }
        composable(
            route = Routes.CATEGORIES
        ) {
            CategoriesScreen(
                onNavigate = {
                    navHostController.navigate(it.route)
                }
            )
        }
        composable(Routes.FAVORITES) {

        }
    }
}

