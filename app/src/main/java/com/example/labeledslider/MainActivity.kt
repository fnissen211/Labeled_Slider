package com.example.labeledslider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.TintableBackgroundView
import com.example.labeledslider.ui.theme.LabeledSliderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabeledSliderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Sliders(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Sliders(modifier: Modifier = Modifier) {
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    var position by remember { mutableStateOf(Location.NONE) }

    Column(modifier) {
        if (position == Location.NONE) {
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.secondary,
                    activeTrackColor = MaterialTheme.colorScheme.secondary,
                    inactiveTickColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
                valueRange = 0f..50f
            )
        }

        if (position == Location.TOP) {
            Text(text = sliderPosition.toString())
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.tertiary,
                    activeTrackColor = MaterialTheme.colorScheme.secondary,
                    inactiveTickColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
                valueRange = 0f..50f
            )
        }

        if (position == Location.BOTTOM) {
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it }
            )
            Text(text = sliderPosition.toString())
        }

        if (position == Location.LEFT) {
            Row {
                Text(text = sliderPosition.toString())
                Slider(
                    value = sliderPosition,
                    onValueChange = { sliderPosition = it }
                )
            }

        }

        if (position == Location.RIGHT) {
            Row {
                Slider(
                    value = sliderPosition,
                    onValueChange = { sliderPosition = it }
                )
                Text(text = sliderPosition.toString())
            }
        }

        Row {
            Text(text = "Top")
            RadioButton(
                selected = position == Location.TOP,
                onClick = { position = Location.TOP }
            )
        }

        Row {
            Text(text = "Bottom")
            RadioButton(
                selected = position == Location.BOTTOM,
                onClick = { position = Location.BOTTOM }
            )
        }

        Row {
            Text(text = "Left")
            RadioButton(
                selected = position == Location.LEFT,
                onClick = { position = Location.LEFT }
            )
        }

        Row {
            Text(text = "Right")
            RadioButton(
                selected = position == Location.RIGHT,
                onClick = { position = Location.RIGHT }
            )
        }

    }
}

@Composable
@Preview(showBackground = true)
fun SlidersPreview() {
    Sliders()
}

enum class Location {
    TOP, BOTTOM, LEFT, RIGHT, NONE
}