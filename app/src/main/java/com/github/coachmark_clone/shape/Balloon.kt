package com.github.coachmark_clone.shape

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.coachmark_clone.util.CoachMarkDefaults
import com.github.coachmark_clone.util.toPx

/**
 * default tooltip shape provided from the library
 * @param arrow configurations of arrow to be shown in the view
 * @param modifier modifier to be applied in the composable
 * @param padding content padding of the view
 * @param cornerRadius corner radius of balloon shape
 * @param shadowElevation elevation when balloon is visible
 * @param bgColor background color of balloon
 * @param content content to be rendered inside balloon
 */
@Composable
public fun Balloon(
    arrow: Arrow,
    modifier: Modifier = Modifier,
    padding: PaddingValues = CoachMarkDefaults.Balloon.padding,
    cornerRadius: Dp = CoachMarkDefaults.Balloon.cornerRadius,
    shadowElevation: Dp = CoachMarkDefaults.Balloon.shadowElevation,
    bgColor: Color = Color.White,
    content: @Composable BoxScope.() -> Unit,
) {
    val density = LocalDensity.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 30.dp)
//            .background(bgColor)
            .graphicsLayer {
                this.shadowElevation = shadowElevation.toPx(density)
                this.shape = RoundedCornerShape(cornerRadius)
            }
            .drawBehind {
                drawArrowRoundRect(
                    arrow = arrow,
                    density = density,
                    radius = cornerRadius,
                    bg = bgColor,
                )
            }
            .padding(
                start = arrow.startPadding,
                end = arrow.endPadding,
                top = arrow.topPadding,
                bottom = arrow.bottomPadding,
            )
//            .padding(padding)
            .then(modifier),
        content = content,
    )
    PaddingValues().calculateBottomPadding()
}

private fun balloonShape(
    arrow: Arrow,
    density: Density,
    radius: Dp,
) = GenericShape { size, _ ->

    addRoundRect(
        RoundRect(
            left = arrow.startPadding.toPx(density),
            right = size.width - arrow.endPadding.toPx(density),
            top = arrow.topPadding.toPx(density),
            bottom = size.height - arrow.bottomPadding
                .toPx(density),
            cornerRadius = CornerRadius(radius.toPx(density)),
        ),
    )

    addPath(arrow.draw(size, density))
}

fun DrawScope.drawArrowRoundRect(
    arrow: Arrow,
    density: Density,
    radius: Dp,
    bg: Color = Color.Gray,
) {
//    color: Color,
//    topLeft: Offset = Offset.Zero,
//    size: Size = this.size.offsetSize(topLeft),
//    cornerRadius: CornerRadius = CornerRadius.Zero,

    drawRoundRect(
        color = bg,
        topLeft = Offset(
            0f,
            0f,
        ),
        size = this.size,
        cornerRadius = CornerRadius(radius.toPx(density)),
    )
    val recSize = 40f
    translate() {
        val rectSize = Size(recSize, recSize)
        val rect =
            Rect(
                Offset(
                    this.size.width
                        .div(2)
                        .minus(recSize.div(2)),
                    -recSize / 1.4f,
                ),
                rectSize,
            )
        val trianglePath = Path().apply {
            moveTo(rect.topCenter.x, rect.topCenter.y)
            lineTo(rect.bottomRight.x, rect.bottomRight.y)
            lineTo(rect.bottomLeft.x, rect.bottomLeft.y)
            close()
        }
        translate(0f, 0f) {
            drawIntoCanvas {
                it.drawOutline(
                    outline = Outline.Generic(trianglePath),
                    paint = Paint().apply {
                        color = bg
                        pathEffect = PathEffect.cornerPathEffect(rect.maxDimension / 3)
                    },
                )
            }
        }
    }
}
