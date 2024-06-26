package com.github.coachmark_clone.util

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import com.github.coachmark_clone.model.TooltipConfig

internal const val ANIMATION_DURATION = 500

internal fun Int.toDp(density: Density) = toFloat().toDp(density)

internal fun Float.toDp(density: Density) = with(density) { toDp() }

internal fun Dp.toPx(density: Density) = with(density) { toPx() }

@Composable
internal fun <T> rememberMutableStateOf(value: T, key: Any = true) = remember(key) {
    mutableStateOf(value)
}

internal fun Modifier.clickable(
    showRipple: Boolean = true,
    onClick: () -> Unit,
) = this.composed {
    clickable(
        indication = if (showRipple) LocalIndication.current else null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick,
    )
}

internal fun buildPath(block: Path.() -> Unit): Path {
    return Path().apply {
        block()
        close()
    }
}

internal fun coachMarkLog(vararg log: String) {
    println("UnifyCoachMarkLogs : ${log.joinToString(", ")}")
}

/**
 * utility to highlight the view when tooltip is visible
 * it draws the cuts to highlight the view when overlay is showing
 */
public fun DrawScope.highlightActualView(
    toolTip: TooltipConfig,
    density: Density,
    alpha: Float,
) {
    val path = toolTip.highlightedViewShape.pathToHighlight(
        density = density,
        size = Size(
            width = toolTip.layout.width.toFloat()-64f,
            height = toolTip.layout.height.toFloat(),
        ),
    ).apply {
        translate(
            Offset(
                x = toolTip.layout.startX+32f,
                y = toolTip.layout.startY,
            ),
        )
    }

    drawPath(
        path = path,
        color = Color.White,
        alpha = alpha,
        blendMode = BlendMode.DstOut,
    )
//    drawRect(
//        color = Color.Black,
//        size = Size(10f,10f),
//        topLeft = Offset(0f,0f)
//    )
}

internal fun log(msg: String) {
    println("CoachMark | $msg")
}
