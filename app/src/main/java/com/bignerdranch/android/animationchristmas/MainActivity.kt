package com.bignerdranch.android.animationchristmas

import android.content.Intent
import android.os.Bundle
import android.transition.Slide
import android.transition.Visibility
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bignerdranch.android.animationchristmas.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationChristmasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BlizzardBlue
                ) {
                 MyActivity()
                }
            }
        }
    }
}


@Composable
fun MyActivity()
{
    val context = LocalContext.current
    val state = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    var visible by remember { mutableStateOf(false)}
    Column() {

        AnimatedVisibility(visibleState = state,
        enter = slideInVertically(initialOffsetY = {-50})
        + fadeIn()) {
            Row() {
                TextButton(onClick = { visible = !visible}){
                    Text(text = "Happy New Year!",
                        textAlign = TextAlign.Start,
                        fontSize = 36.sp, color = White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(8.dp)
                            .background(color = BluePurple, shape = CircleShape)
                            .size(310.dp, 60.dp)
                            .padding(8.dp))
                }

                Image(painter = painterResource(R.drawable.christmas_branch),
                    contentDescription = "branch",
                    modifier = Modifier
                        .size(66.dp)
                        .padding(6.dp))
            }

            if (!visible){
                AnimatedVisibility(visible = !visible,
                    enter = fadeIn(),
                    exit = fadeOut()){
                    Row(horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically) {
                        Spacer(modifier = Modifier
                            .height(550.dp)
                            .width(100.dp))
                        IconButton(onClick = { },
                            enabled = false){
                            Image(painter = painterResource(R.drawable.cookie),
                                contentDescription = "cookie")
                        }
                    }
                }
            }
        }

        AnimatedVisibility(
            visible = visible,
            enter = slideInHorizontally()
                + fadeIn(),
            exit = shrinkHorizontally()) {
            Text(text = "Take your present",
                textAlign = TextAlign.Start,
                fontSize = 28.sp, color = White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(18.dp))
        }

        Spacer(modifier = Modifier.height(100.dp))

        AnimatedVisibility(
            visible = visible,
            enter = slideInHorizontally()
                    + fadeIn(),
            exit = shrinkHorizontally()
        ) {
            Spacer(modifier = Modifier.width(26.dp))
            Box(contentAlignment = Alignment.TopEnd) {
                Spacer(modifier = Modifier.width(300.dp))
                IconButton(onClick = { context.startActivity(Intent(context, SnowActivity::class.java)) },
                modifier = Modifier.offset(40.dp, 80.dp)) {
                    Image(painter = painterResource(R.drawable.snowball),
                        contentDescription = "snowball",
                        modifier = Modifier
                            .size(90.dp)
                    )
                }
                Spacer(modifier = Modifier.width(26.dp).height(10.dp))
            }

            Box(contentAlignment = Alignment.BottomEnd) {
                IconButton(onClick = { context.startActivity(Intent(context, TreeAcivity::class.java)) }) {
                    Image(painter = painterResource(R.drawable.present),
                        contentDescription = "present",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(8.dp)
                    )
                }
            }
        }

        AnimatedVisibility(
            visible = visible,
            enter = slideInHorizontally()
                    + fadeIn(),
            exit = shrinkHorizontally()
        ) {
            Box() {
                Spacer(modifier = Modifier.width(16.dp))
                Image(painter = painterResource(R.drawable.santa),
                    contentDescription = "santa",
                    modifier = Modifier
                        .size(310.dp)
                        .padding(8.dp),
                    alignment = Alignment.Center)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AnimationChristmasTheme {
    }
}