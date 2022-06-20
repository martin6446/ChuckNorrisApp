package com.example.chucknorrisjokesapp.ui.random_joke_item

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.chucknorrisjokesapp.R
import com.example.chucknorrisjokesapp.ui.theme.ChuckNorrisJokesAppTheme
import com.example.chucknorrisjokesapp.ui.theme.LightOrange

@Composable
fun RandomJokeScreen(viewModel: ChuckNorrisRandomJokeViewModel = hiltViewModel()) {

    val infiniteTransition = rememberInfiniteTransition()


    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 5f,
        animationSpec = infiniteRepeatable(
            animation = tween(100, delayMillis = 1000, easing = LinearEasing ),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.chucknorris_logo_coloured),
            contentDescription = "",
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer {
                    rotationZ = angle
                }
        )
        CategoryChip(category = viewModel.category, modifier = Modifier.padding(bottom = 16.dp)) {
            viewModel.resetCategory()
        }
        ChuckNorrisJoke(viewModel.joke, modifier = Modifier.weight(1f))
        Button(
            onClick = { viewModel.onEvent(RandomJokeEvent.GetNewJoke(viewModel.category)) },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = "Get Another Joke!", color = MaterialTheme.colors.onPrimary)
        }
    }
}

@Composable
fun CategoryChip(category: String?, modifier: Modifier, onIconClick: () -> Unit) {
    Surface(
        modifier = modifier
            .border(
            2.dp,
            color = MaterialTheme.colors.secondary,
            RoundedCornerShape(50.dp)
        ),
        shape = RoundedCornerShape(50.dp),
        elevation = 2.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            category?.let {
                Text(
                    text = category
                        .replaceFirstChar {
                            it.uppercase()
                        },
                    modifier = Modifier.padding(start = 12.dp)
                )
                IconButton(onClick = { onIconClick() }) {
                    Icon(Icons.Default.Cancel, contentDescription = "", tint = LightOrange)
                }
            }
        }
    }
}

@Preview
@Composable
fun CategoryChipPreview() {
    CategoryChip(category = "Animals", modifier = Modifier, onIconClick = {})
}

@Composable
fun ChuckNorrisJoke(joke: String, modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        if (joke.isEmpty()) {
            CircularProgressIndicator(
                modifier = Modifier.size(40.dp),
                strokeWidth = 5.dp
            )
        } else {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                elevation = 5.dp
            ) {
                Text(
                    text = joke,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun JokePreview() {
    ChuckNorrisJokesAppTheme {
        ChuckNorrisJoke(joke = "haha I'm funny", Modifier)
    }
}