package com.devloper.squad.feature_beer.presentation

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.devloper.squad.feature_beer.domain.model.BeerItemDomain
import org.koin.androidx.compose.getViewModel
import kotlin.math.roundToInt

@Composable
fun BeerList(
    viewModel: BeerViewModel = getViewModel()
) {
    val beerDomain = viewModel.beers.value.beerDomain.toList()
    LazyColumn {
        items(beerDomain.size) { index ->
            BeerCard(beerItem = beerDomain[index]) { id ->
                viewModel.onEvent(BeerViewModel.Event.OnNavigate(id))
            }
        }
    }
}

@Preview
@Composable
fun BeerCard(
    @PreviewParameter(BeerItemDomainPreviewParameterProvider::class) beerItem: BeerItemDomain,
    onItemClick: (id: Int) -> Unit = { it ->
        Log.d("Ura", "$it")
    }
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(bottom = 10.dp)
            .fillMaxWidth()
            .clickable(role = Role.Image, onClickLabel = "dujuejej") { onItemClick(beerItem.id) }
    ) {
        Image(
            painter = rememberImagePainter(beerItem.imageUrl),
            contentDescription = "dkasmdlksal",
            modifier = Modifier
                .size(128.dp)
                .semantics {
                    customActions = listOf(
                        CustomAccessibilityAction("dsa", Testaaa())
                    )
                }
        )
        Text(beerItem.name)
    }
}

class Testaaa(val desc: String = "aaa", val t: String = "ppp") : Function0<Boolean> {
    override fun invoke(): Boolean {
        return true;
    }
}
/*@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun BeerCard(
    @PreviewParameter(BeerItemDomainPreviewParameterProvider::class) beerItem: BeerItemDomain,
    onItemClick: (id: Int) -> Unit = { it ->
        Log.d("Ura", "$it")
    }
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Button(
        onClick = { *//* do something *//* },
        interactionSource = interactionSource
    ) {
        Text(if (isPressed) "Pressed!" else "Not pressed")
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.pointerInput(Unit) {
            detectTransformGestures { centroid, pan, zoom, rotation -> }
            *//*detectTapGestures(
                onPress = { *//**//* Called when the gesture starts *//**//* },
                onDoubleTap = { *//**//* Called on Double Tap *//**//* },
                onLongPress = { *//**//* Called on Long Press *//**//* },
                onTap = { *//**//* Called on Tap *//**//* }
            )*//*
        }
    ) {
        Image(
            painter = rememberImagePainter(beerItem.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .size(128.dp)
                .layoutId("test123")
                .semantics {
                    contentDescription = "dsa"
                }
                .dynatrace("dsad", "tefdfsast")
                .clickable(onClickLabel = "test", role = Role.Image) {
                    Dynatrace.applyUserPrivacyOptions(
                        UserPrivacyOptions
                            .builder()
                            .withDataCollectionLevel(DataCollectionLevel.USER_BEHAVIOR)
                            .withCrashReportingOptedIn(true)
                            .withCrashReplayOptedIn(true)
                            .build()
                    )
                }
        )
        Text(beerItem.name, modifier = Modifier.clickable(role = Role.Switch) {

        })
        Button(
            onClick = {
                Log.d("ClickableCompose", "Hello button!")
            },
        ) {
            Text("Test button", color = Color.Black)
        }
    }
}*/

@Preview
@Composable
fun IconToggleButtonExamples() {

    var checked by rememberSaveable { mutableStateOf(false) }
    val likeIcon =
        if (checked) Icons.Filled.ThumbUp
        else Icons.Outlined.ThumbUp

    // IconToggleButton - Custom Color
    IconToggleButton(
        checked = checked,
        onCheckedChange = { checked = it },
        modifier = Modifier
            .clip(CircleShape)
            .background(
                if (checked) MaterialTheme.colors.primary
                else Color.Transparent
            )
    ) {
        Icon(likeIcon, "Like")
    }
}

@Composable
fun Checkbox() {
    val isChecked = remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .clickable(onClick = {
                isChecked.value = !isChecked.value
            })

    ) {
        Checkbox(
            checked = isChecked.value,
            enabled = true,
            onCheckedChange = { checked ->
                isChecked.value = checked
            },
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colors.primarySurface,
                uncheckedColor = MaterialTheme.colors.primary,
            ),
            modifier = Modifier.padding(end = 16.dp)
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Preview(
    name = "Checkbox",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
)
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
@Composable
fun Test1() {
    Row {
        Checkbox()
        IconToggleButtonExamples()
        Switch(checked = false, onCheckedChange = {})
        Button(
            onClick = {
                Log.d("ClickableCompose", "Hello button!")
            },
        ) {
            Text("Test button", color = Color.Black)
        }

        Text(text = "Ura", modifier = Modifier.combinedClickable(role = Role.Button,
            onClick = {
                Log.d("ClickableCompose", "Click")
            },
            onLongClick = {
                Log.d("ClickableCompose", "LongClick")
            },
            onDoubleClick = {
                Log.d("ClickableCompose", "DoubleClick")
            }
        ))
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun A() {
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

class BeerItemDomainPreviewParameterProvider : PreviewParameterProvider<BeerItemDomain> {
    override val values = sequenceOf(
        BeerItemDomain(imageUrl = "https://www.example.com/image.jpg", name = "Ura")
    )
}
