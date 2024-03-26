package com.github.coachmark_clone

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import com.github.coachmark_clone.model.OverlayClickEvent
import com.github.coachmark_clone.overlay.UnifyOverlayEffect
import com.github.coachmark_clone.scope.CoachMarkScope
import com.github.coachmark_clone.scope.CoachMarkScopeImpl
import com.github.coachmark_clone.ui.CoachMarkImpl
import com.github.coachmark_clone.util.CoachMarkDefaults
import com.github.coachmark_clone.util.CoachMarkKey

public val LocalCoachMarkScope: ProvidableCompositionLocal<CoachMarkScope> =
    compositionLocalOf { error("CompositionLocal CoachMarkScope not present") }

@Composable
public fun UnifyCoachmark(
    tooltip: @Composable CoachMarkScope.(CoachMarkKey) -> Unit,
    overlayEffect: UnifyOverlayEffect = CoachMarkDefaults.Overlay.background,
    onOverlayClicked: (CoachMarkKey) -> OverlayClickEvent = { CoachMarkDefaults.Overlay.clickEvent },
    content: @Composable CoachMarkScope.() -> Unit
) {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current

    val scope = remember(onOverlayClicked) {
        CoachMarkScopeImpl(
            onOverlayClicked = onOverlayClicked,
            density = density,
            layoutDirection = layoutDirection
        )
    }

    CompositionLocalProvider(LocalCoachMarkScope provides scope) {
        CoachMarkImpl(
            overlayEffect = overlayEffect,
            content = content,
            coachMarkScope = scope,
            tooltip = tooltip
        )
    }
}
