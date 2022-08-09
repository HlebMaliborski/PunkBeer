package com.devloper.squad.feature_beer.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.GestureCancellationException
import androidx.compose.foundation.gestures.PressGestureScope
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.onLongClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Density
import androidx.compose.ui.util.fastAll
import androidx.compose.ui.util.fastAny
import com.dynatrace.android.agent.Dynatrace
import com.dynatrace.android.agent.conf.DataCollectionLevel
import com.dynatrace.android.agent.conf.UserPrivacyOptions
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex


fun Modifier.clickable1(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "clickable"
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {
    val onClickableTest = {
        Log.d("Ura123", "Ura123")
        onClick()
    }
    Modifier.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = onClickableTest,
        role = role,
        indication = LocalIndication.current,
        interactionSource = remember { MutableInteractionSource() }
    )
}

val a = {
    Log.d("Ura888", "Ura888")
}
val semanticModifier1 = Modifier.semantics(mergeDescendants = true) {
    // b/156468846:  add long click semantics and double click if needed
    onClick(action = { a; true }, label = "ds")
    onLongClick(action = { a; true }, label = "dsa")

}

fun Modifier.dynatrace1(text: String? = null, description: String): Modifier {
    return this.then(
        DynatraceModifier(text, description)
    )
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.dynatrace(text: String? = null, description: String): Modifier {
    return this.pointerInput(Unit) {
        detectTapAndPressUnconsumed {
            Dynatrace.applyUserPrivacyOptions(
                UserPrivacyOptions
                    .builder()
                    .withDataCollectionLevel(DataCollectionLevel.USER_BEHAVIOR)
                    .withCrashReportingOptedIn(true)
                    .withCrashReplayOptedIn(true)
                    .build()
            )
            Log.d("Test888", "$text $description")
        }
    }

}

class PressGestureScopeImpl1(
    density: Density
) : PressGestureScope, Density by density {
    private var isReleased = false
    private var isCanceled = false
    private val mutex = Mutex(locked = false)

    /**
     * Called when a gesture has been canceled.
     */
    fun cancel() {
        isCanceled = true
        mutex.unlock()
    }

    /**
     * Called when all pointers are up.
     */
    fun release() {
        isReleased = true
        mutex.unlock()
    }

    /**
     * Called when a new gesture has started.
     */
    fun reset() {
        mutex.tryLock() // If tryAwaitRelease wasn't called, this will be unlocked.
        isReleased = false
        isCanceled = false
    }

    override suspend fun awaitRelease() {
        if (!tryAwaitRelease()) {
            throw GestureCancellationException("The press gesture was canceled.")
        }
    }

    override suspend fun tryAwaitRelease(): Boolean {
        if (!isReleased && !isCanceled) {
            mutex.lock()
        }
        return isReleased
    }
}

val NoPressGesture1: suspend PressGestureScope.(Offset) -> Unit = { }

class DynatraceModifier(val text: String? = null, val description: String) : Modifier.Element {
    operator fun invoke() {
        Log.d("Ura12345", "$text $description")
    }
}

suspend fun PointerInputScope.detectTapAndPressUnconsumed(
    onPress: suspend PressGestureScope.(Offset) -> Unit = NoPressGesture1,
    onTap: ((Offset) -> Unit)? = null
) {
    val pressScope = PressGestureScopeImpl1(this)
    forEachGesture {
        coroutineScope {
            pressScope.reset()
            awaitPointerEventScope {

                val down = awaitFirstDown(requireUnconsumed = false).also { it.consumeDownChange() }

                if (onPress !== NoPressGesture1) {
                    launch { pressScope.onPress(down.position) }
                }

                val up = waitForUpOrCancellationInitial()
                if (up == null) {
                    pressScope.cancel() // tap-up was canceled
                } else {
                    pressScope.release()
                    onTap?.invoke(up.position)
                }
            }
        }
    }
}

suspend fun AwaitPointerEventScope.waitForUpOrCancellationInitial(): PointerInputChange? {
    while (true) {
        val event = awaitPointerEvent(PointerEventPass.Initial)
        if (event.changes.fastAll { it.changedToUp() }) {
            // All pointers are up
            return event.changes[0]
        }

        if (event.changes.fastAny { it.consumed.downChange || it.isOutOfBounds(size) }) {
            return null // Canceled
        }

        // Check for cancel by position consumption. We can look on the Final pass of the
        // existing pointer event because it comes after the Main pass we checked above.
        val consumeCheck = awaitPointerEvent(PointerEventPass.Final)
        if (consumeCheck.changes.fastAny { it.positionChangeConsumed() }) {
            return null
        }
    }
}
