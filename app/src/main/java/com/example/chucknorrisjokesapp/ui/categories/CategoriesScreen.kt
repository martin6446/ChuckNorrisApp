package com.example.chucknorrisjokesapp.ui.categories

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.chucknorrisjokesapp.utils.UiEvent

@Composable
fun CategoriesScreen(
    viewModel: ChuckNorrisCategoriesViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit
) {

    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    onNavigate(event)
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Categories")
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            items(viewModel.categories) { category ->
                Text(
                    text = category.uppercase(),
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .clickable { viewModel.onEvent(CategoriesEvent.OnCategoryClick(category)) }
                        .padding(
                            top = 16.dp,
                            bottom = 16.dp,
                            start = 16.dp
                        )
                )
                Divider()
            }
        }

    }
}