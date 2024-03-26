package com.github.coachmark_clone.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.layoutId
import com.github.coachmark_clone.overlay.TooltipId
import com.github.coachmark_clone.overlay.UnifyOverlayEffect
import com.github.coachmark_clone.scope.CoachMarkScope
import com.github.coachmark_clone.scope.CoachMarkScopeImpl
import com.github.coachmark_clone.util.CoachMarkKey
import com.github.coachmark_clone.util.clickable
import com.github.coachmark_clone.util.rememberTooltipHolder

@Composable
internal fun CoachMarkImpl(
    overlayEffect: UnifyOverlayEffect,
    coachMarkScope: CoachMarkScopeImpl,
    tooltip: @Composable CoachMarkScope.(CoachMarkKey) -> Unit,
    content: @Composable (CoachMarkScope.() -> Unit),
) = with(overlayEffect) {
    val currentTooltip = coachMarkScope.currentVisibleTooltip?.let {
        rememberTooltipHolder(
            item = it,
            animationSpec = it.animationState.tooltipAnimationSpec,
            onAlphaValueUpdated = it.animationState.onAlphaValueUpdated
        )
    }

    val previousTooltip = coachMarkScope.lastVisibleTooltip?.let {
        rememberTooltipHolder(
            item = it,
            animationSpec = it.animationState.tooltipAnimationSpec,
            onAlphaValueUpdated = it.animationState.onAlphaValueUpdated
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        content(coachMarkScope)

        coachMarkScope.Overlay(
            modifier = Modifier
                .fillMaxSize()
                .run {
                    if (currentTooltip?.isVisible == true) {
                        clickable(showRipple = false, onClick = coachMarkScope::onOverlayClicked)
                    } else this
                }
                .alpha(
                    animateFloatAsState(
                        targetValue = if (currentTooltip?.isVisible == true) 1f else 0f,
                        animationSpec = overlayEffect.overlayAnimationSpec,
                        label = "OverlayAlphaAnimation"
                    ).value
                ),
            currentTooltip = currentTooltip,
            previousTooltip = previousTooltip
        ) {
            Tooltip(
                tooltipHolder = currentTooltip,
                modifier = Modifier.layoutId(TooltipId.current),
            ) {
                coachMarkScope.tooltip(it)
            }
            Tooltip(
                tooltipHolder = previousTooltip,
                modifier = Modifier.layoutId(TooltipId.previous),
            ) {
                coachMarkScope.tooltip(it)
            }
        }
    }
}
