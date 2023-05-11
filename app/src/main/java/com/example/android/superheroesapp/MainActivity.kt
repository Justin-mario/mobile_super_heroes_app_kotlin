package com.example.android.superheroesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.android.superheroesapp.service.HeroesApp
import com.example.android.superheroesapp.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HeroesApp()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HeroesAppPreviewLightTheme() {
    SuperheroesTheme(darkTheme = false) {
//        HeroList()
        HeroesApp()
    }
}

@Preview(showBackground = true)
@Composable
fun HeroesAppPreviewDarkTheme() {
    SuperheroesTheme(darkTheme = true) {
//        HeroList()
        HeroesApp()
    }
}