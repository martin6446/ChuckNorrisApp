package com.example.chucknorrisjokesapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.SentimentVerySatisfied
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.chucknorrisjokesapp.utils.Routes

data class BottomNavItem(
    val route: String,
    val name: String,
    val icon: ImageVector
)
val navigationItems = listOf(
    BottomNavItem(
        Routes.RANDOM_JOKE + "?category={category}",
        "Random Joke",
        Icons.Default.SentimentVerySatisfied
    ),
    BottomNavItem(
        Routes.CATEGORIES,
        "Categories",
        Icons.Default.List
    ),
    BottomNavItem(
        Routes.FAVORITES,
        "Favorites",
        Icons.Default.Favorite
    )
)