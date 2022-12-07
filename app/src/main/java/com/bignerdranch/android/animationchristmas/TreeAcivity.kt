package com.bignerdranch.android.animationchristmas

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bignerdranch.android.animationchristmas.ui.theme.*

class TreeAcivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationChristmasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BlizzardBlue
                ) {
                    ChristmasTree()
                    MyCard()
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun MyCard(){
    val state = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {
        AnimatedVisibility(visibleState = state,
            enter = scaleIn(transformOrigin = TransformOrigin.Center)
                    + fadeIn(),
            exit = fadeOut()){
            Text(text = "Here's your Christmas tree\nHave a great year!",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .background(color = BluePurple, shape = CircleShape)
                    .size(350.dp, 140.dp)
                    .padding(8.dp)
                    )
        }
        Spacer(modifier = Modifier.height(200.dp))
    }

}



@Composable
fun ChristmasTree(){

    val state = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    Column(verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally) {

        AnimatedVisibility(visibleState = state,
        enter = slideInVertically(initialOffsetY = {-60},
            animationSpec = tween(800))
            + fadeIn(),
        exit = fadeOut()) {
            Box() {
                Image(painter = painterResource(R.drawable.snowflakes),
                    contentDescription = "tree",
                    modifier = Modifier
                        .size(450.dp, 380.dp))

            }
        }

        AnimatedVisibility(visibleState = state,
        enter = slideInVertically(initialOffsetY = {60},
            animationSpec = tween(800))
        + fadeIn(),
        exit = slideOutVertically()) {
            Box(contentAlignment = Alignment.BottomCenter) {
                Image(painter = painterResource(R.drawable.christmas_tree),
                    contentDescription = "tree", modifier = Modifier.size(290.dp),
                    alignment = Alignment.BottomCenter)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    AnimationChristmasTheme {
    }
}