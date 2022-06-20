package com.example.chucknorrisjokesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.chucknorrisjokesapp.ui.navigation.BottomNavItem
import com.example.chucknorrisjokesapp.ui.navigation.Navigation
import com.example.chucknorrisjokesapp.ui.navigation.navigationItems
import com.example.chucknorrisjokesapp.ui.theme.ChuckNorrisJokesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChuckNorrisJokesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                items = navigationItems,
                                navController = navController,
                                modifier = Modifier,
                                onItemClicked = { navController.navigate(it.route) }
                            )
                        }
                    ) {
                        Navigation(
                            navHostController = navController,
                            modifier = Modifier.padding(it)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier,
    onItemClicked: (BottomNavItem) -> Unit
) {
    BottomNavigation(
        modifier = modifier,
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.primary
    ) {
        val backStackEntry = navController.currentBackStackEntryAsState()
        items.forEach { item ->
            val selected = backStackEntry.value?.destination?.route == item.route
            BottomNavigationItem(
                selected = selected,
                onClick = {
                    if (!selected) {
                        onItemClicked(item)
                    }
                },
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(item.icon, contentDescription = "", modifier.size(20.dp))
                        if (selected) {
                            Text(text = item.name, fontSize = 10.sp)
                        }
                    }
                }
            )
        }
    }
}


