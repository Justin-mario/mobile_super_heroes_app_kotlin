package com.example.android.superheroesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android.superheroesapp.model.Hero
import com.example.android.superheroesapp.model.HeroesRepository
import com.example.android.superheroesapp.model.HeroesRepository.heroes
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroesApp(modifier: Modifier = Modifier ) {
    Scaffold(topBar = { HeroTopAppBar()}) {
        LazyColumn(contentPadding = it){
            items(heroes) {hero ->
                HeroItem(hero = hero, modifier = Modifier.padding(8.dp))
            }

        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroTopAppBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(title = {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.displayLarge)
        }
    } , modifier = Modifier)
}

@Composable
fun HeroItem(hero: Hero, modifier: Modifier = Modifier) {
    Card(modifier = modifier
        .fillMaxSize()
        .height(86.dp),

    shape = MaterialTheme.shapes.medium,
        ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            ) {
            HeroInfo(heroName = hero.nameRes, heroDescription = hero.descriptionRes)
            Spacer(modifier = Modifier.weight(1f))
           Box(modifier = Modifier.height(72.dp)){
               Image(painter = painterResource(id = hero.imageRes), contentDescription = stringResource(
                   id = hero.nameRes), modifier = Modifier
                   .clip(MaterialTheme.shapes.small),
                   contentScale =ContentScale.FillHeight)

           }
        }

    }
}

@Composable
fun HeroInfo(@StringRes heroName: Int, @StringRes heroDescription: Int, modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        Text(text = stringResource(id = heroName),
            style = MaterialTheme.typography.displaySmall,)
        Text(text = stringResource(id = heroDescription),
            style = MaterialTheme.typography.bodyLarge, modifier = Modifier,

        )

    }
}

@Composable
fun HeroList(){
   heroes.forEach { hero ->  HeroItem(hero = hero) }
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