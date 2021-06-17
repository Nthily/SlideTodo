package com.github.nthily.slidetodo


import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import java.time.format.TextStyle
import kotlin.math.roundToInt

enum class state{
    Start, End
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun SlideTodo(
    slideHeight: Dp = 60.dp,
    slideWidth: Dp = 400.dp,
    slideColor: Color,
    navigationIcon: @Composable () -> Unit,
    navigationIconPadding: Dp = 0.dp,
    endIcon: @Composable () -> Unit,
    widthAnimationMillis: Int = 1000,
    text: String? = null,
    textStyle: androidx.compose.ui.text.TextStyle? = null,
    elevation: Dp = 0.dp
){


    val iconSize = slideHeight - 10.dp

    val slideDistance = with(LocalDensity.current) {
        (slideWidth - iconSize - 15.dp).toPx()
    }


    val swipeableState = rememberSwipeableState(initialValue = state.Start)

    var flag by remember { mutableStateOf(iconSize) }

    if(swipeableState.currentValue == state.End){
        flag = 0.dp
    }

    val iconSizeAnimation by animateDpAsState(targetValue = flag, tween(1000))

    val textAlpha by animateFloatAsState(
        targetValue = if(swipeableState.offset.value != 0f) (1 - swipeableState.progress.fraction) else 1f
    )

    val width by animateDpAsState(
        targetValue = if(iconSizeAnimation == 0.dp) slideHeight else slideWidth,
        tween(widthAnimationMillis)
    )


    Surface(
        shape = CircleShape,
        modifier = Modifier
            .height(slideHeight)
            .width(width),
        color = slideColor,
        elevation = elevation
    ) {
        Box(
            modifier = Modifier
                .padding(5.dp),
        ){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CompositionLocalProvider(
                    LocalContentAlpha provides textAlpha,
                ) {
                    text?.let { it ->
                        textStyle?.let{
                            Text(
                                text = text,
                                style = it
                            )
                        }
                        Text(it)
                    }
                }
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ){
                Surface(
                    shape = CircleShape,
                    modifier = Modifier
                        .size(iconSizeAnimation)
                        .padding(navigationIconPadding)
                        .swipeable(
                            state = swipeableState,
                            anchors = mapOf(
                                0f to state.Start,
                                slideDistance to state.End
                            ),
                            thresholds = { _, _ -> FractionalThreshold(0.9f) },
                            orientation = Orientation.Horizontal
                        )
                        .offset {
                            IntOffset(swipeableState.offset.value.roundToInt(), 0)
                        },
                ) {
                    navigationIcon()
                }
            }
            AnimatedVisibility(visible = width == slideHeight) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    endIcon()
                }
            }
        }
    }
}