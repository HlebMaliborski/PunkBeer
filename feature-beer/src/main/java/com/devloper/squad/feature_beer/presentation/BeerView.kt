package com.devloper.squad.feature_beer.presentation

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.devloper.squad.feature_beer.domain.model.BeerItemDomain
import com.dynatrace.android.agent.Dynatrace
import com.dynatrace.android.agent.conf.DataCollectionLevel
import com.dynatrace.android.agent.conf.UserPrivacyOptions
import org.koin.androidx.compose.getViewModel
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun BeerList(
    viewModel: BeerViewModel = getViewModel()
) {
    Test1()
   /* val beerDomain = viewModel.beers.value.beerDomain.toList()
    LazyColumn {
        items(beerDomain.size) { index ->
            BeerCard(beerItem = beerDomain[index]) { id ->
                viewModel.onEvent(BeerViewModel.Event.OnNavigate(id))
            }
        }
    }*/
}

@OptIn(ExperimentalFoundationApi::class)
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
        modifier = Modifier.pointerInput(Unit) {
            detectTransformGestures { centroid, pan, zoom, rotation -> }
            /*detectTapGestures(
                onPress = { *//* Called when the gesture starts *//* },
                onDoubleTap = { *//* Called on Double Tap *//* },
                onLongPress = { *//* Called on Long Press *//* },
                onTap = { *//* Called on Tap *//* }
            )*/
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
}

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
    }
}

class BeerItemDomainPreviewParameterProvider : PreviewParameterProvider<BeerItemDomain> {
    override val values = sequenceOf(
        BeerItemDomain(imageUrl = "https://www.example.com/image.jpg", name = "Ura")
    )
}

