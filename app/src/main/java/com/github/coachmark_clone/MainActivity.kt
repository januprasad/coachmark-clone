package com.github.coachmark_clone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.github.coachmark_clone.ui.theme.CoachmarkcloneTheme
import com.github.coachmark_clone.UnifyCoachmarkDemo

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoachmarkcloneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CoachmarkDemo()
                }
            }
        }
    }
}
@Composable
fun CoachmarkDemo() {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        UnifyCoachmarkDemo()
    }
}
