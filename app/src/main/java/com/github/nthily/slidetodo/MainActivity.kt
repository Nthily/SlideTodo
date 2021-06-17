package com.github.nthily.slidetodo

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.nthily.slidetodo.ui.theme.SlideTodoTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SlideTodoTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ){
                        SlideTodo(
                            slideColor = Color(0xFF0079D3),
                            navigationIcon = {
                                Icon(
                                    Icons.Filled.ArrowForward,
                                    contentDescription = null,
                                    modifier = Modifier.padding(8.dp)
                                )
                            },
                            endIcon = {
                                Icon(
                                    Icons.Filled.Done,
                                    contentDescription = null,
                                )
                            },
                            slideHeight = 160.dp,
                            slideWidth =  400.dp,
                            text = "Slide to Play Game!!",
                            textStyle = TextStyle(
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.W700
                            )
                        )
                        SlideTodo(
                            slideColor = Color(0xFFFF5252),
                            navigationIcon = {
                                Icon(
                                    Icons.Filled.ArrowForward,
                                    contentDescription = null,
                                    modifier = Modifier.padding(8.dp)
                                )
                            },
                            endIcon = {
                                Icon(
                                    Icons.Filled.Done,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            },
                            slideHeight = 60.dp,
                            slideWidth = 300.dp,
                            text = "Slide to Unlock",
                            textStyle = TextStyle(
                                color = Color.White
                            )
                        )
                        SlideTodo(
                            slideColor = Color(0xFFE040FB),
                            navigationIcon = {
                                Icon(
                                    Icons.Filled.ArrowForward,
                                    contentDescription = null,
                                    modifier = Modifier.padding(8.dp)
                                )
                            },
                            endIcon = {
                                Icon(
                                    Icons.Filled.Done,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            },
                            slideHeight = 60.dp,
                            slideWidth =  200.dp
                        )
                        SlideTodo(
                            slideColor = Color(0xFFFF6E40),
                            navigationIcon = {
                                Icon(
                                    Icons.Filled.ArrowForward,
                                    contentDescription = null,
                                    modifier = Modifier.padding(8.dp)
                                )
                            },
                            endIcon = {
                                Icon(
                                    Icons.Filled.Done,
                                    contentDescription = null,
                                    modifier = Modifier.padding(8.dp),
                                    tint = Color.White
                                )
                            },
                            slideHeight = 60.dp,
                            slideWidth =  150.dp
                        )
                        SlideTodo(
                            slideColor = Color(0xFFFFD740),
                            navigationIcon = {
                                Icon(
                                    Icons.Filled.ArrowForward,
                                    contentDescription = null,
                                    modifier = Modifier.padding(8.dp)
                                )
                            },
                            endIcon = {
                                Icon(
                                    Icons.Filled.Done,
                                    contentDescription = null,
                                    modifier = Modifier.padding(8.dp),
                                    tint = Color.White
                                )
                            },
                            slideHeight = 60.dp,
                            slideWidth =  400.dp,
                            elevation = 8.dp,
                            navigationIconPadding = 8.dp
                        )
                        SlideTodo(
                            slideColor = Color(0xFF64FFDA),
                            navigationIcon = {
                                Icon(
                                    Icons.Filled.ArrowForward,
                                    contentDescription = null,
                                    modifier = Modifier.padding(8.dp)
                                )
                            },
                            endIcon = {
                                Icon(
                                    Icons.Filled.Done,
                                    contentDescription = null,
                                    modifier = Modifier.padding(8.dp),
                                    tint = Color.White
                                )
                            },
                            slideHeight = 60.dp,
                            slideWidth =  400.dp,
                            elevation = 9.dp,
                            navigationIconPadding = 12.dp
                        )
                    }
                }
            }
        }
    }
}