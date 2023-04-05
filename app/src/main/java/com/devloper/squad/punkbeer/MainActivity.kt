package com.devloper.squad.punkbeer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devloper.squad.navigation.NavigationManager
import org.koin.android.ext.android.inject
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {

    private val navigationManager: NavigationManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                Slider()
                RangeSlider()
            }
        }
    }
}


@Composable
fun Slider() {

    var sliderValue by remember {
        mutableStateOf(0f) // pass the initial value
    }

    Slider(
        value = sliderValue,
        onValueChange = { sliderValue_ ->
            sliderValue = sliderValue_
        },
        onValueChangeFinished = {},
        valueRange = 0f..10f,
        modifier = Modifier.semantics {
            contentDescription = "Custom slider"
        }
    )

    Text(text = sliderValue.toString())
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RangeSlider() {
    var sliderValues by remember {
        mutableStateOf(1f..20f) // pass the initial values
    }

    RangeSlider(
        value = sliderValues,
        onValueChange = { sliderValues_ ->
            sliderValues = sliderValues_
        },
        valueRange = 1f..20f,
    )

    Text(text = "Start: ${sliderValues.start}, End: ${sliderValues.endInclusive}")
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeData() {
    val width = 350.dp
    val squareSize = 50.dp

    val swipeableState = rememberSwipeableState("A")
    val sizePx = with(LocalDensity.current) { (width - squareSize).toPx() }
    val anchors = mapOf(0f to "A", sizePx / 2 to "B", sizePx to "C")

    Box(
        modifier = Modifier
            .width(width)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Horizontal
            )
            .background(Color.Black)
    ) {
        Box(
            Modifier
                .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                .size(squareSize)
                .background(Color.Red),
            contentAlignment = Alignment.Center
        ) {
            Text(swipeableState.currentValue, color = Color.White, fontSize = 24.sp)
        }
    }
}

@Preview
@Composable
fun HelloContent() {
    Column(modifier = Modifier.padding(16.dp)) {
        var name by remember { mutableStateOf("") }
        if (name.isNotEmpty()) {
            Text(
                text = "Hello, $name!",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h5
            )
        }
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
    }
}