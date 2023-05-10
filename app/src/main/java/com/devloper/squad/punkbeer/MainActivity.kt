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
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okio.Buffer
import org.koin.android.ext.android.inject
import java.nio.charset.StandardCharsets.UTF_8
import kotlin.math.roundToInt
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis


class MainActivity : ComponentActivity() {

    private val navigationManager: NavigationManager by inject()
    val string =
        "{\"mobileAgentConfig\": {\"maxBeaconSizeKb\":150,\"selfmonitoring\":true,\"maxSessionDurationMins\":360,\"maxEventsPerSession\":100000,\"sessionTimeoutSec\":600,\"sendIntervalSec\":120,\"visitStoreVersion\":2,\"maxCachedCrashesCount\":10,\"rageTapConfig\":{\"tapDuration\":100,\"dispersionRadius\":100,\"timespanDifference\":300,\"minimumNumberOfTaps\":3},\"replayConfig\":{\"protocolVersion\":1,\"selfmonitoring\":3}},\"appConfig\": {\"capture\":1,\"captureLifecycle\":1,\"reportCrashes\":1,\"reportErrors\":1,\"trafficControlPercentage\":100,\"replayConfig\":{\"capture\":false,\"imageRetentionTimeInMinutes\":1440},\"ipMasking\":\"None\",\"applicationId\":\"1f6803a9-53b6-45d4-9e5e-a2ba83255453\"},\"dynamicConfig\": {\"serverId\":4, \"switchServer\":false, \"status\":\"OK\"},\"timestamp\": 1683631162842}"

    val parserConfig = ManualParserConfig()

    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    val jsonAdapter: JsonAdapter<TestAgentConfig> = moshi.adapter(TestAgentConfig::class.java)

    val moshi1 = Moshi.Builder().build()
    val jsonAdapter1: JsonAdapter<TestAgentConfig> = moshi1.adapter(TestAgentConfig::class.java)

    fun parseManually(): Long {
        return measureNanoTime {
            val source = Buffer().apply {
                writeString(string, UTF_8)
            }
            val reader = JsonReader.of(source)
            parserConfig.parse(reader)
        }
    }

    fun parseReflectGenerated(): Long {
        return measureNanoTime {
            jsonAdapter.fromJson(string)
        }
    }

    fun parseGenerated(): Long {
        return measureNanoTime {
            jsonAdapter1.fromJson(string)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var manualTime by remember { mutableStateOf("Time") }
            var generatedReflectTime by remember { mutableStateOf("Time") }
            var generatedTime by remember { mutableStateOf("Time") }
            Column(modifier = Modifier.padding(10.dp)) {
                Row {
                    Button(onClick = {
                        manualTime = parseManually().toString()
                    }, modifier = Modifier.padding(10.dp)) {
                        Text(text = "Manual Parsing")
                    }

                    Text(manualTime)
                }

                Row {
                    Button(onClick = {
                        generatedReflectTime = parseReflectGenerated().toString()
                    }, modifier = Modifier.padding(10.dp)) {
                        Text(text = "Reflect Parsing")
                    }

                    Text(generatedReflectTime)
                }

                Row {
                    Button(onClick = {
                        generatedTime = parseGenerated().toString()
                    }, modifier = Modifier.padding(10.dp)) {
                        Text(text = "Generated Parsing")
                    }

                    Text(generatedTime)
                }
            }
        }
        /*setContent {
            Row() {
                Column(modifier = Modifier.padding(16.dp)) {
                    val refreshScope = rememberCoroutineScope()
                    var refreshing by remember { mutableStateOf(false) }
                    var itemCount by remember { mutableStateOf(3) }

                    fun refresh() = refreshScope.launch {
                        refreshing = true
                        delay(1500)
                        itemCount += 1
                        refreshing = false
                    }

                    val state = rememberPullRefreshState(refreshing, ::refresh)

                    Box(
                        Modifier
                            .pullRefresh(state)
                            .width(100.dp)
                    ) {
                        LazyColumn(modifier = Modifier.semantics { contentDescription = "Pull refresh" }) {
                            if (!refreshing) {
                                items(itemCount) {
                                    ListItem { Text(text = "Item ${itemCount - it}") }
                                }
                            }
                        }

                        PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
                    }


                    val pagerState = rememberPagerState()

                    HorizontalPager(pageCount = 10, state = pagerState, modifier = Modifier
                        .semantics {
                            contentDescription = "Horizontal pager"
                        }
                        .width(100.dp)) { page ->
                        Text(
                            text = "Page: $page",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                        )
                    }

                    val coroutineScope = rememberCoroutineScope()
                    Button(onClick = {
                        coroutineScope.launch {
                            // Call scroll to on pagerState
                            pagerState.animateScrollToPage(5)
                        }
                    }, modifier = Modifier.semantics { contentDescription = "swipe button" }) {
                        Text("Jump to Page 5")
                    }
                    Slider()
                    RangeSlider()
                }

                VerticalPager(
                    pageCount = 10,
                    modifier = Modifier.semantics {
                        contentDescription = "Vertical Pager"
                    }
                ) { page ->
                    Box(
                        modifier = Modifier
                            .background(Color.Blue)
                            .width(100.dp)
                            .height(100.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = page.toString(), fontSize = 32.sp)
                    }
                }
            }
            *//*val navController = rememberNavController()
            PunkbeerTheme {
                LaunchedEffect(Unit) {
                    navigationManager.commands.collect { command ->
                        if (command.destination.isNotEmpty()) {
                            navController.navigate(command.destination)
                        }
                    }
                }
                Surface(color = MaterialTheme.colors.background) {
                    NavigationComponent(navController)
                }
            }*//*
        }*/
    }
}

var a = 0

@Composable
fun Slider() {

    var sliderValue by remember {
        mutableStateOf(0f) // pass the initial value
    }
    val onValueChange: (Float) -> Unit = { res ->
        sliderValue = res
    }

    Slider(value = sliderValue,
        onValueChange = onValueChange,
        onValueChangeFinished = { },
        valueRange = 0f..10f,
        modifier = Modifier.semantics {
            contentDescription = "Custom slider"
        })
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
                .background(Color.Red), contentAlignment = Alignment.Center) {
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
                text = "Hello, $name!", modifier = Modifier.padding(bottom = 8.dp), style = MaterialTheme.typography.h5
            )
        }
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
    }
}