package com.github.coachmark_clone

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.coachmark_clone.model.HighlightedViewConfig
import com.github.coachmark_clone.model.OverlayClickEvent
import com.github.coachmark_clone.model.ToolTipPlacement
import com.github.coachmark_clone.overlay.DimOverlayEffect
import com.github.coachmark_clone.scope.enableCoachMark
import com.github.coachmark_clone.shape.Arrow
import com.github.coachmark_clone.shape.Balloon
import com.github.coachmark_clone.util.CoachMarkKey

public enum class Keys { Text1, Text2, TextStart, TextBottom, TextTop }

@Composable
public fun UnifyCoachmarkDemo() {
    UnifyCoachmark(
        tooltip = { Tooltip(it) },
        overlayEffect = DimOverlayEffect(Color.Black.copy(alpha = .5f)),
        onOverlayClicked = { OverlayClickEvent.GoNext },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            PlotTextsAndUseLocalCoachMarkScope()
            Button(
                onClick = {
                    show(Keys.Text1)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally),
            ) {
                Text(text = "Highlight 1")
            }
            Button(
                onClick = {
                    show(*Keys.values())
                },
                modifier = Modifier.align(Alignment.CenterHorizontally),
            ) {
                Text(text = "Highlight All")
            }
            Button(onClick = { show(Keys.TextBottom, Keys.TextTop) }) {
                Text(text = "Highlight Some")
            }
        }
    }
}

@Composable
private fun ColumnScope.PlotTextsAndUseLocalCoachMarkScope() {
    CoachMarkTargetText("Will show tooltip 1", Alignment.Start, Keys.Text1, ToolTipPlacement.End)

    CoachMarkTargetText("Will show tooltip 2", Alignment.Start, Keys.Text2, ToolTipPlacement.Bottom)

    CoachMarkTargetText(
        "Will show tooltip to left",
        Alignment.End,
        Keys.TextStart,
        ToolTipPlacement.Start,
    )

    CoachMarkTargetText(
        "Will show tooltip below",
        Alignment.CenterHorizontally,
        Keys.TextBottom,
        ToolTipPlacement.Bottom,
    )

    CoachMarkTargetText(
        "Will show tooltip above",
        Alignment.CenterHorizontally,
        Keys.TextTop,
        ToolTipPlacement.Top,
    )
}

@Composable
private fun ColumnScope.CoachMarkTargetText(
    text: String,
    alignment: Alignment.Horizontal,
    key: Keys,
    placement: ToolTipPlacement,
) {
    val coachMarkScope = LocalCoachMarkScope.current

    Text(
        text = text,
        modifier = Modifier
            .align(alignment)
            .enableCoachMark(
                key = key,
                toolTipPlacement = placement,
                highlightedViewConfig = HighlightedViewConfig(
                    shape = HighlightedViewConfig.Shape.Rect(12.dp),
                    padding = PaddingValues(8.dp),
                ),
                coachMarkScope = coachMarkScope,
            )
            .padding(16.dp),
        color = Color.Black,
    )
}

@Composable
private fun Tooltip(key: CoachMarkKey) {
    when (key) {
        Keys.Text1 -> {
            Balloon(arrow = Arrow.Start()) {
                Text(text = "Highlighting Text1", color = Color.White)
            }
        }

        Keys.Text2 -> {
            Balloon(arrow = Arrow.Start()) {
                Text(text = "Highlighting Text2", color = Color.White)
            }
        }

        Keys.TextStart -> {
            Balloon(arrow = Arrow.End()) {
                Text(text = "A tooltip to the left", color = Color.White)
            }
        }

        Keys.TextBottom -> {
            Balloon(arrow = Arrow.Top()) {
                Text(text = "A tooltip below", color = Color.White)
            }
        }

        Keys.TextTop -> {
            Balloon(arrow = Arrow.Bottom()) {
                Text(text = "A tooltip above", color = Color.White)
            }
        }
    }
}
