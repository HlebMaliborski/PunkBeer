package com.devloper.squad.feature_beer.presentation

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

class BeerItemDomainPreviewParameterProvider : PreviewParameterProvider<BeerItemDomain> {
    override val values = sequenceOf(
        BeerItemDomain(imageUrl = "https://www.example.com/image.jpg", name = "Ura")
    )
}

/*@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Preview(widthDp = 300, heightDp = 600)
@Composable
fun ButtonSample() {

    val counter = remember { mutableStateOf(0) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Text(
            modifier = Modifier
                .weight(2f)
                .semantics {
                    onClick {
                        Log.d("Ura", "Ura")
                        true
                    }
                }
                .combinedClickable {
                    Log.d("ura", "ura")
                },
            *//*.pointerInput(Unit) {
              detectTapGestures(
                onLongPress = {
                  // perform some action here..
                }
              )
            }*//*
            text = "Counter value: ${counter.value}",
            style = MaterialTheme.typography.h4,
        )

        val onClick: () -> Unit = {
            Log.d("dsa", "dsa")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                onClick()
                counter.value++
            },
            modifier = Modifier
                .clickable {
                    counter.value++
                }
                .onGloballyPositioned { }
                .semantics {
                    role = Role.Button
                },
        ) {
            Text("Increment", color = Color.White)
        }
    }
}*/
