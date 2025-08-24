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
import com.creative.compose_shadow.shadow.ShadowUiState
import com.creative.compose_shadow.ui.custom.ColorPicker
import com.creative.compose_shadow.ui.custom.ColorPickerState
import com.creative.compose_shadow.ui.custom.TitleHeader
import com.creative.compose_shadow.ui.custom.TitleSlider
import com.creative.compose_shadow.shadow.shadow
import com.creative.compose_shadow.ui.theme.ComposeShadowTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ComposeShadowTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var shadowUiState by remember {
                        mutableStateOf(ShadowUiState(Color.Black.copy(alpha = 0.2f), 16f, 16f, 16f, 0f, 0f, 0f, 64f))
                    }
                    val text by remember { mutableStateOf("Draw your content here") }

                    Column(
                        modifier = Modifier
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(100.dp)
                                .shadow(
                                    shadowUiState.shadowColor,
                                    borderRadius = shadowUiState.shadowBorderRadius.dp,
                                    offsetX = shadowUiState.offsetX.dp,
                                    offsetY = shadowUiState.offsetY.dp,
                                    spread = shadowUiState.spread.dp,
                                    blurRadius = shadowUiState.blurRadius.dp
                                )
                                .fillMaxWidth()
                                .height(shadowUiState.height.dp)
                                .clip(RoundedCornerShape(shadowUiState.mainRadius.dp))
                                .background(Color.White)
                        ) {
                            Text(
                                text,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                        LazyColumn(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(1.dp)
                        ) {
                            stickyHeader {
                                TitleHeader(getString(R.string.item_shape))
                            }
                            item {
                                TitleSlider(title = getString(R.string.height), from = 0f, to = 200f, currentValue = shadowUiState.height) {
                                    shadowUiState = shadowUiState.copy(height = it)
                                }
                            }
                            item {
                                TitleSlider(title = getString(R.string.radius), from = 0f, to = 80f, currentValue = shadowUiState.mainRadius) {
                                    shadowUiState = shadowUiState.copy(mainRadius = it)
                                }
                            }
                            stickyHeader {
                                TitleHeader(getString(R.string.shadow))
                            }
                            item {
                                TitleSlider(title = getString(R.string.radius), from = 0f, to = 80f, currentValue = shadowUiState.shadowBorderRadius) {
                                    shadowUiState = shadowUiState.copy(shadowBorderRadius = it)
                                }
                            }
                            item {
                                TitleSlider(title = getString(R.string.offsetx), from = 0f, to = 80f, currentValue = shadowUiState.offsetX) {
                                    shadowUiState = shadowUiState.copy(offsetX = it)
                                }
                            }
                            item {
                                TitleSlider(title = getString(R.string.offsety), from = 0f, to = 80f, currentValue = shadowUiState.offsetY) {
                                    shadowUiState = shadowUiState.copy(offsetY = it)
                                }
                            }
                            item {
                                TitleSlider(title = getString(R.string.spread), from = 0f, to = 80f, currentValue = shadowUiState.spread) {
                                    shadowUiState = shadowUiState.copy(spread = it)
                                }
                            }
                            item {
                                TitleSlider(title = getString(R.string.blur_radius), from = 0f, to = 80f, currentValue = shadowUiState.blurRadius) {
                                    shadowUiState = shadowUiState.copy(blurRadius = it)
                                }
                            }
                            stickyHeader {
                                TitleHeader(getString(R.string.shadow_color))
                            }
                            item {
                                ColorPicker(
                                    state = ColorPickerState(
                                        shadowUiState.shadowColor.red,
                                        shadowUiState.shadowColor.green,
                                        shadowUiState.shadowColor.blue,
                                        shadowUiState.shadowColor.alpha
                                    )
                                ) {
                                    shadowUiState = shadowUiState.copy(shadowColor = it)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
