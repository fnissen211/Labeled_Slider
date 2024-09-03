package com.example.labeledslider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.TintableBackgroundView
import com.example.labeledslider.ui.theme.LabeledSliderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabeledSliderTheme {
                ScaffoldExample()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Sliders(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    var presses by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Top app bar")
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Settings, contentDescription = "Add")
                    }
                }
            )
        },
        bottomBar = { MyButtonBar() },
        // Generally, do not use two floating action buttons on the same screen.
        floatingActionButton = {
            FloatingActionButton(onClick = { presses++ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text =
                """
                    This is an example of a scaffold. It uses the Scaffold composable's parameters to create a screen with a simple top app bar, bottom app bar, and floating action button.
                    It also contains some basic inner content, such as this text.
                    You have pressed the floating action button $presses times.
                """.trimIndent(),
            )
            Text(
                text = "More content",
                modifier = Modifier
                    .background(Color.Yellow)
                    .padding(20.dp)
                    //.fillMaxWidth() // not good with CenterHorizontally
                    .align(CenterHorizontally)
                    .clickable { presses++ }
            )
        }
    }
}

@Composable
fun MyButtonBar() {
    // https://developer.android.com/develop/ui/compose/components/app-bars#bottom
    BottomAppBar(
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(Icons.Filled.Check, contentDescription = "Localized description")
            }
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    Icons.Filled.Edit,
                    contentDescription = "Localized description",
                )
            }
            IconButton(onClick = { /* do something */ }) {

            }
            IconButton(onClick = { /* do something */ }) {

            }
        },
        // Generally, do not use two floating action buttons on the same screen.
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* do something */ },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(Icons.Filled.Add, "Localized description")
            }
        }
    )
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

@Preview(showBackground = true)
@Composable
fun ScaffoldPreview() {
    LabeledSliderTheme {
        ScaffoldExample()
    }
}

enum class Location {
    TOP, BOTTOM, LEFT, RIGHT, NONE
}