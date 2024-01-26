package com.creative.compose_shadow.ui.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Created by dan on 27/01/2024
 *
 * Copyright © 2024 1010 Creative. All rights reserved.
 */

@Stable
data class ColorPickerState(val r: Float, val g: Float, val b: Float, val alpha: Float)

@Composable
fun ColorPicker(state: ColorPickerState, onColorChange: (Color)->Unit) {

    LaunchedEffect(key1 = Unit) {
        onColorChange(Color(state.r, state.g, state.b, state.alpha))
    }

    Column(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
        Box(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .size(20.dp)
                .clip(CircleShape)
                .background(Color(state.r, state.g, state.b, state.alpha))
        )
        TitleSlider(title = "R", currentValue = state.r, to = 1.0f) {
            onColorChange(Color(it, state.g, state.b, state.alpha))
        }
        TitleSlider(title = "G", currentValue = state.g) {
            onColorChange(Color(state.r, it, state.b, state.alpha))
        }
        TitleSlider(title = "B", currentValue = state.b) {
            onColorChange(Color(state.r, state.g, it, state.alpha))
        }
        TitleSlider(title = "A", currentValue = state.alpha) {
            onColorChange(Color(state.r, state.g, state.b, it))
        }
    }
}