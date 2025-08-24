package com.creative.compose_shadow.shadow

import androidx.compose.ui.graphics.Color

data class ShadowUiState(
    val shadowColor: Color,
    val mainRadius: Float,
    val shadowBorderRadius: Float,
    val blurRadius: Float,
    val offsetY: Float,
    val offsetX: Float,
    val spread: Float,
    val height: Float
)