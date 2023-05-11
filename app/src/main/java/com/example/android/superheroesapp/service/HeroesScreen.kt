package com.example.android.superheroesapp.service


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.android.superheroesapp.R
import com.example.android.superheroesapp.model.Hero
import com.example.android.superheroesapp.model.HeroesRepository.heroes

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun HeroesApp(modifier: Modifier = Modifier ) {
    Scaffold(topBar = { HeroTopAppBar()}) {
        val visibleState = remember {
            MutableTransitionState(false).apply {
                // Start the animation immediately.
                targetState = true
            }
        }

        // Fade in entry animation for the entire list
        AnimatedVisibility(
            visibleState = visibleState,
            enter = fadeIn(
                animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)
            ),
            exit = fadeOut(),
            modifier = modifier
        ) {
        LazyColumn(contentPadding = it, modifier = modifier){
            itemsIndexed(heroes) { index, hero ->
                HeroItem(
                    hero = hero,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        // Animate each list item to slide in vertically
                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = Spring.StiffnessVeryLow,
                                    dampingRatio = Spring.DampingRatioLowBouncy
                                ),
                                initialOffsetY = { it * (index + 1) } // staggered entrance
                            )
                        )
                )
            }
        }
//            items(HeroesRepository.heroes) { hero ->
//                HeroItem(hero = hero, modifier = Modifier.padding(8.dp))
//            }

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
    } , modifier = modifier)
}

@Composable
fun HeroItem(hero: Hero, modifier: Modifier = Modifier) {
    Card(modifier = modifier
        .fillMaxSize(),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        shape = MaterialTheme.shapes.medium,
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .sizeIn(minHeight = 72.dp),
        ) {
            Column(modifier = modifier.weight(1F)) {
                Text(text = stringResource(id = hero.nameRes),
                    style = MaterialTheme.typography.displaySmall,)
                Text(text = stringResource(id = hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge, modifier = Modifier,

                    )

            }

            Spacer(Modifier.width(16.dp))
            Box(modifier = Modifier
                .size(72.dp)
                .clip(MaterialTheme.shapes.small),
                ){
                Image(painter = painterResource(id = hero.imageRes), contentDescription = stringResource(
                    id = hero.nameRes), modifier = Modifier,
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.FillWidth)

            }
        }

    }
}



//@Composable
//fun HeroList(){
//    HeroesRepository.heroes.forEach { hero ->  HeroItem(hero = hero) }
//}
