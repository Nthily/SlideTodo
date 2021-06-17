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
import kotlin.math.roundToInt

sealed class Status {
    enum class state{
        Start, End
    }
    object Start: Status()
    object End: Status()
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun SlideTodo(
    slideHeight: Dp = 60.dp,
    slideWidth: Dp = 400.dp,
    slideColor: Color,
    navigationIcon: @Composable () -> Unit,
    endIcon: @Composable () -> Unit
){


    val iconSize = slideHeight - 10.dp

    val slideDistance = with(LocalDensity.current) {
        (slideWidth - iconSize - 15.dp).toPx()
    }


    val swipeableState = rememberSwipeableState(initialValue = Status.state.Start)

    var flag by remember { mutableStateOf(iconSize) }

    if(swipeableState.currentValue == Status.state.End){
        flag = 0.dp
    }

    val iconSizeAnimation by animateDpAsState(targetValue = flag, tween(1000))

    val textAlpha by animateFloatAsState(
        targetValue = if(swipeableState.offset.value != 0f) (1 - swipeableState.progress.fraction) else 1f
    )

    val width by animateDpAsState(
        targetValue = if(iconSizeAnimation == 0.dp) slideHeight else slideWidth,
        tween(1000)
    )


    Surface(
        shape = CircleShape,
        modifier = Modifier
            .height(slideHeight)
            .width(width),
        color = slideColor
    ) {
        Box(
            modifier = Modifier
                .padding(5.dp),
        ){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CompositionLocalProvider(LocalContentAlpha.provides(textAlpha)) {
                    Text(
                        text = "Slide to Unlock"
                    )
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
                        .swipeable(
                            state = swipeableState,
                            anchors = mapOf(
                                0f to Status.state.Start,
                                slideDistance to Status.state.End
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