package com.creative.compose_shadow.ui.custom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Created by dan on 27/01/2024
 *
 * Copyright © 2024 1010 Creative. All rights reserved.
 */

@Composable
fun TitleSlider(title: String, from: Float = 0f, to: Float = 1f, currentValue: Float, onSliderChange: (Float) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("$title: $currentValue")
        Slider(
            value = currentValue,
            onValueChange = {
                onSliderChange(it)
            },
            valueRange = from..to
        )
    }
}