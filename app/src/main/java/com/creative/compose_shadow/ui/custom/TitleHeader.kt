package com.creative.compose_shadow.ui.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * Created by dan on 27/01/2024
 *
 * Copyright © 2024 1010 Creative. All rights reserved.
 */

@Composable
fun TitleHeader(title: String) {
    Column {
        Text(title, fontWeight = FontWeight.Black, modifier = Modifier.fillMaxWidth().background(Color.White).padding(4.dp))
        Divider()
    }
}