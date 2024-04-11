package com.github.coachmark_clone.coachmark

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.coachmark_clone.R
import com.github.coachmark_clone.ui.theme.CoachmarkcloneTheme

val minWidthContent: Dp = 328.dp
val TextTextPrimary: Color = Color(0xFF2A2A2E)
val TextTextSecondary: Color = Color(0xFF57575B)
val White: Color = Color(0xFFF1F1FF)
val SurfacePrimary: Color = White


@Composable
fun CoachMarkView() {
    Column(
        Modifier
            .shadow(
                elevation = 6.dp,
                spotColor = Color(0x1A000000),
                ambientColor = Color(0x1A000000),
            )
            .background(color = SurfacePrimary, RoundedCornerShape(8.dp))
            .widthIn(max = 428.dp)
            .wrapContentHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .width(296.dp)
                .height(148.dp),
            painter = painterResource(id = R.drawable.robot),
            contentDescription = "image description",
            contentScale = ContentScale.FillBounds,
        )
        Text(
            text = "Coachmark Title",
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(700),
                color = TextTextPrimary,
            ),
        )
        Text(
            text = "This is place for bring description to a particular feature that warrants the users focus.",
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight(400),
                color = TextTextSecondary,
            ),
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.End),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.weight(1F),
                text = "1 of 4",
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(600),
                    color = TextTextSecondary,
                ),
            )
            OutlinedButton(onClick = { }) {
                Text(
                    text = "Skip Tour",
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(600),
                        color = TextTextPrimary,
                    ),
                )
            }
            Button(onClick = { }) {
                Text(
                    text = "Next",
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(600),
                        color = White,
                    ),
                )
            }
        }
    }
}

@Composable
@Preview
private fun CoachMarkViewPreview() {
    CoachmarkcloneTheme {
        CoachMarkView()
    }
}
