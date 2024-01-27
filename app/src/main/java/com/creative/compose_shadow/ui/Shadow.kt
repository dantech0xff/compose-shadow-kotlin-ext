package com.creative.compose_shadow.ui

import android.graphics.BlurMaskFilter
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Created by dan on 27/01/2024
 *
 * Copyright © 2024 1010 Creative. All rights reserved.
 */

private val paint = Paint()
@Stable
fun Modifier.shadow(
    color: Color = Color.Black,
    borderRadius: Dp = 0.dp,
    blurRadius: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    spread: Dp = 0f.dp,
    modifier: Modifier = Modifier
) = then(
    modifier.drawBehind {
        drawIntoCanvas {
            val frameworkPaint = paint.asFrameworkPaint()
            frameworkPaint.reset()
            val spreadPixel = spread.toPx()
            val leftPixel = (0f - spreadPixel) + offsetX.toPx()
            val topPixel = (0f - spreadPixel) + offsetY.toPx()
            val rightPixel = (this.size.width + offsetX.toPx() + spreadPixel)
            val bottomPixel = (this.size.height + offsetY.toPx() + spreadPixel)

            frameworkPaint.let { fwPaint ->
                fwPaint.isAntiAlias = true
                if (blurRadius > 0.dp) {
                    fwPaint.maskFilter = BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL)
                }
                fwPaint.color = color.toArgb()
            }
            it.drawRoundRect(
                left = leftPixel, top = topPixel,
                right = rightPixel, bottom = bottomPixel,
                radiusX = borderRadius.toPx(), radiusY = borderRadius.toPx(),
                paint
            )
        }
    }
)