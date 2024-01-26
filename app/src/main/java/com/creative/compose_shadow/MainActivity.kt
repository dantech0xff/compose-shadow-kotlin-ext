package com.creative.compose_shadow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.creative.compose_shadow.ui.custom.ColorPicker
import com.creative.compose_shadow.ui.custom.ColorPickerState
import com.creative.compose_shadow.ui.custom.TitleHeader
import com.creative.compose_shadow.ui.custom.TitleSlider
import com.creative.compose_shadow.ui.shadow
import com.creative.compose_shadow.ui.theme.ComposeShadowTheme

data class ShadowState(
    val shadowColor: Color,
    val mainRadius: Float,
    val shadowBorderRadius: Float,
    val blurRadius: Float,
    val offsetY: Float,
    val offsetX: Float,
    val spread: Float,
    val height: Float
)

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ComposeShadowTheme {
                Surface(
                    modifier = Modifier
                        .safeGesturesPadding()
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var shadowState by remember {
                        mutableStateOf(ShadowState(Color.Black.copy(alpha = 0.2f), 16f, 16f, 16f, 0f, 0f, 0f, 64f))
                    }
                    val text by remember { mutableStateOf("Draw your content here") }

                    Column(
                        modifier = Modifier
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(vertical = 60.dp, horizontal = 42.dp)
                                .shadow(
                                    shadowState.shadowColor,
                                    borderRadius = shadowState.shadowBorderRadius.dp,
                                    offsetX = shadowState.offsetX.dp,
                                    offsetY = shadowState.offsetY.dp,
                                    spread = shadowState.spread.dp,
                                    blurRadius = shadowState.blurRadius.dp
                                )
                                .fillMaxWidth()
                                .height(shadowState.height.dp)
                                .clip(RoundedCornerShape(shadowState.mainRadius.dp))
                                .background(Color.White)
                        ) {
                            Text(
                                text,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                        LazyColumn(
                            modifier = Modifier, verticalArrangement = Arrangement.spacedBy(1.dp)
                        ) {
                            stickyHeader {
                                TitleHeader(getString(R.string.item_shape))
                            }
                            item {
                                TitleSlider(title = getString(R.string.height), from = 0f, to = 200f, currentValue = shadowState.height) {
                                    shadowState = shadowState.copy(height = it)
                                }
                            }
                            item {
                                TitleSlider(title = getString(R.string.radius), from = 0f, to = 80f, currentValue = shadowState.mainRadius) {
                                    shadowState = shadowState.copy(mainRadius = it)
                                }
                            }
                            stickyHeader {
                                TitleHeader(getString(R.string.shadow))
                            }
                            item {
                                TitleSlider(title = getString(R.string.radius), from = 0f, to = 80f, currentValue = shadowState.shadowBorderRadius) {
                                    shadowState = shadowState.copy(shadowBorderRadius = it)
                                }
                            }
                            item {
                                TitleSlider(title = getString(R.string.offsetx), from = 0f, to = 80f, currentValue = shadowState.offsetX) {
                                    shadowState = shadowState.copy(offsetX = it)
                                }
                            }
                            item {
                                TitleSlider(title = getString(R.string.offsety), from = 0f, to = 80f, currentValue = shadowState.offsetY) {
                                    shadowState = shadowState.copy(offsetY = it)
                                }
                            }
                            item {
                                TitleSlider(title = getString(R.string.spread), from = 0f, to = 80f, currentValue = shadowState.spread) {
                                    shadowState = shadowState.copy(spread = it)
                                }
                            }
                            item {
                                TitleSlider(title = getString(R.string.blur_radius), from = 0f, to = 80f, currentValue = shadowState.blurRadius) {
                                    shadowState = shadowState.copy(blurRadius = it)
                                }
                            }
                            stickyHeader {
                                TitleHeader(getString(R.string.shadow_color))
                            }
                            item {
                                ColorPicker(
                                    state = ColorPickerState(
                                        shadowState.shadowColor.red,
                                        shadowState.shadowColor.green,
                                        shadowState.shadowColor.blue,
                                        shadowState.shadowColor.alpha
                                    )
                                ) {
                                    shadowState = shadowState.copy(shadowColor = it)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
