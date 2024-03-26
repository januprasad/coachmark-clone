package com.github.coachmark_clone

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.coachmark_clone.shape.Arrow
import com.github.coachmark_clone.shape.Balloon

@Composable
public fun BalloonShapeDemo() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Balloon(arrow = Arrow.Start()) {
            Text(text = "Start")
        }
        Balloon(arrow = Arrow.End()) {
            Text(text = "End")
        }
        Balloon(arrow = Arrow.Top()) {
            Text(text = "Top")
        }
        Balloon(arrow = Arrow.Bottom()) {
            Text(text = "Bottom")
        }
    }
}