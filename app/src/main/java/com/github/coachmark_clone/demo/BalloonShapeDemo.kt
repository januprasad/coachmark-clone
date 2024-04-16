package com.github.coachmark_clone

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        Balloon(arrow = Arrow.TopStart()) {
            Text(text = "Start")
        }
        Balloon(arrow = Arrow.TopEnd()) {
            Text(text = "End")
        }
        Balloon(arrow = Arrow.TopCenter()) {
            Text(text = "Top")
        }
        Balloon(arrow = Arrow.BottomCenter()) {
            Text(text = "Bottom")
        }
    }
}