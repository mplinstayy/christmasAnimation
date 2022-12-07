package com.bignerdranch.android.animationchristmas

import android.os.Bundle
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bignerdranch.android.animationchristmas.ui.theme.AnimationChristmasTheme
import com.bignerdranch.android.animationchristmas.ui.theme.BlizzardBlue
import com.bignerdranch.android.animationchristmas.ui.theme.BluePurple
import com.bignerdranch.android.animationchristmas.ui.theme.White

class SnowActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationChristmasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BlizzardBlue
                ) {
                    Frozen()
                    MyCardSnow()
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun MyCardSnow(){
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
            Text(text = "You got frozen!!\nNow you're a snowman",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .background(color = BluePurple, shape = CircleShape)
                    .size(350.dp, 100.dp)
                    .padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.height(200.dp))
    }
}

@Composable
fun Frozen(){
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
            exit = fadeOut()
        ) {
            Box() {
                Image(painter = painterResource(R.drawable.snowflakes),
                    contentDescription = "tree",
                    modifier = Modifier
                        .size(450.dp, 380.dp))

            }
        }

        AnimatedVisibility(visibleState = state,
            enter = slideInVertically(initialOffsetY = {60},
                animationSpec = tween(800)
            )
                    + fadeIn(),
            exit = slideOutVertically()
        ) {
            Box(contentAlignment = Alignment.BottomCenter) {
                Image(painter = painterResource(R.drawable.snowman),
                    contentDescription = "snowman", modifier = Modifier.size(290.dp),
                    alignment = Alignment.BottomCenter)
            }
        }

    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    AnimationChristmasTheme {
    }
}