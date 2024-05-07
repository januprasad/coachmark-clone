package com.github.coachmark_clone

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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

public enum class Keys { Text1, Text2, Text3, Text4, TextStart, TextBottom }

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
                .verticalScroll(rememberScrollState())
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
//            PlotTextsAndUseLocalCoachMarkScope()
//            CoachMarkTargetText(
//                "Will show tooltip 1",
//                Alignment.Start,
//                Keys.Text1,
//                ToolTipPlacement.Bottom,
//            )
            CoachMarkTargetButton(
                "Bank Accounts",
                Alignment.Start,
                Keys.Text1,
                ToolTipPlacement.Bottom,
            )

            CoachMarkTargetButton(
                "UPI Settings",
                Alignment.Start,
                Keys.Text2,
                ToolTipPlacement.Bottom,
            )
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxWidth(),
//            ) {
//                item {
                    CoachMarkTargetButton(
                        "Sent to Phone",
                        Alignment.CenterHorizontally,
                        Keys.Text3,
                        ToolTipPlacement.Bottom,
                    )
//                }
//            }

            CoachMarkTargetButton(
                "Pay Electricity Bill",
                Alignment.Start,
                Keys.Text4,
                ToolTipPlacement.Top,
            )

            CoachMarkTargetButton(
                "Transactions",
                Alignment.Start,
                Keys.TextStart,
                ToolTipPlacement.Top,
            )

            CoachMarkTargetButton(
                "Enable Push Notifications",
                Alignment.Start,
                Keys.TextBottom,
                ToolTipPlacement.Top,
            )
            CoachMarkTargetButton(
                "Enable Push Notifications",
                Alignment.Start,
                Keys.TextBottom,
                ToolTipPlacement.Top,
            )
            CoachMarkTargetButton(
                "Enable Push Notifications",
                Alignment.Start,
                Keys.TextBottom,
                ToolTipPlacement.Top,
            )
            CoachMarkTargetButton(
                "Enable Push Notifications",
                Alignment.Start,
                Keys.TextBottom,
                ToolTipPlacement.Top,
            )

            Button(
                onClick = {
                    show(Keys.Text1)
                },
                modifier = Modifier.align(Alignment.Start),
            ) {
                Text(text = "Highlight Bank Accounts")
            }
            Button(
                onClick = {
                    show(Keys.Text2)
                },
                modifier = Modifier.align(Alignment.Start),
            ) {
                Text(text = "Highlight UPI Settings")
            }
            Button(
                onClick = {
                    show(Keys.Text3)
                },
                modifier = Modifier.align(Alignment.Start),
            ) {
                Text(text = "Highlight Sent to Phone")
            }
            Button(
                onClick = {
                    show(Keys.Text4)
                },
                modifier = Modifier.align(Alignment.Start),
            ) {
                Text(text = "Highlight Electricity Bill")
            }
            Button(
                onClick = {
                    show(Keys.TextStart)
                },
                modifier = Modifier.align(Alignment.Start),
            ) {
                Text(text = "Highlight Transactions")
            }
            Button(
                onClick = {
                    show(Keys.TextBottom)
                },
                modifier = Modifier.align(Alignment.Start),
            ) {
                Text(text = "Highlight Push Notifications")
            }

            Button(
                onClick = {
                    show(*Keys.values())
                },
                modifier = Modifier.align(Alignment.Start),
            ) {
                Text(text = "Highlight All")
            }
        }
    }
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
            .fillMaxWidth()
            .align(alignment)
            .enableCoachMark(
                key = key,
                toolTipPlacement = placement,
                highlightedViewConfig = HighlightedViewConfig(
                    shape = HighlightedViewConfig.Shape.Rect(8.dp),
                    padding = PaddingValues(8.dp),
                ),
                coachMarkScope = coachMarkScope,
            )
            .padding(16.dp),
        color = Color.Black,
    )
}

@Composable
private fun ColumnScope.CoachMarkTargetButton(
    text: String,
    alignment: Alignment.Horizontal,
    key: Keys,
    placement: ToolTipPlacement,
) {
    val coachMarkScope = LocalCoachMarkScope.current

    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .align(alignment)
            .enableCoachMark(
                key = key,
                toolTipPlacement = placement,
                highlightedViewConfig = HighlightedViewConfig(
                    shape = HighlightedViewConfig.Shape.Rect(8.dp),
                    padding = PaddingValues(8.dp),
                ),
                coachMarkScope = coachMarkScope,
            )
            .padding(horizontal = 8.dp),
        onClick = {},
    ) {
        Text(text = text, color = Color.White)
    }
}

@Composable
private fun Tooltip(key: CoachMarkKey) {
    when (key) {
        Keys.Text1 -> {
            Balloon(arrow = Arrow.TopCenter()) {
                Text(text = "A tooltip top center", color = Color.Black)
            }
        }

        Keys.Text2 -> {
            Balloon(arrow = Arrow.TopStart()) {
                Text(text = "A tooltip top start", color = Color.Black)
            }
        }

        Keys.Text3 -> {
            Balloon(arrow = Arrow.TopEnd()) {
//                CoachMarkView()
                Text(text = "A tooltip top end", color = Color.Black)
            }
        }

        Keys.Text4 -> {
            Balloon(arrow = Arrow.BottomCenter()) {
//                CoachMarkView()
                Text(text = "A tooltip bottom center", color = Color.Black)
            }
        }

        Keys.TextStart -> {
            Balloon(arrow = Arrow.BottomStart()) {
                Text(text = "A tooltip bottom start", color = Color.Black)
            }
        }

        Keys.TextBottom -> {
            Balloon(arrow = Arrow.BottomEnd()) {
                Text(text = "A tooltip bottom end", color = Color.Black)
            }
        }
    }
}
