package com.example.appdev2_assignment2.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appdev2_assignment2.R
import com.example.compose.darkBluePrimary
import com.example.compose.lightBlueOnPrimary
import com.example.compose.lightBluePrimaryContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCarPage(
    modifier: Modifier = Modifier,
    onCarCreated: (Car) -> Unit
) {
    var selectedEngine by remember { mutableStateOf<Engine?>(null) }
    var selectedWheel by remember { mutableStateOf<Wheel?>(null) }
    var carName by remember { mutableStateOf("") }

    val engines = listOf(
        Engine("Engine A", "200 HP"),
        Engine("Engine B", "250 HP"),
        // Add more engines as needed
    )

    val wheels = listOf(
        Wheel("Wheel A", 18),
        Wheel("Wheel B", 20),
        // Add more wheels as needed
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(lightBluePrimaryContainer)
    ) {
        CarAttributeSelection(
            title = "Choose Engine",
            items = engines,
            selectedItem = selectedEngine,
            onItemSelected = { engine -> selectedEngine = engine }
        )

        CarAttributeSelection(
            title = "Choose Wheel",
            items = wheels,
            selectedItem = selectedWheel,
            onItemSelected = { wheel -> selectedWheel = wheel }
        )

        OutlinedTextField(
            value = carName,
            onValueChange = { carName = it },
            label = { Text("Car Name") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        Button(
            onClick = {
                if (selectedEngine != null && selectedWheel != null && carName.isNotBlank()) {
                    val newCar = Car(carName, selectedEngine!!, selectedWheel!!)
                    onCarCreated(newCar)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .background(darkBluePrimary)
        ) {
            Text("Create Car", color = lightBlueOnPrimary)
        }
    }
}

@Composable
fun <T : CarAttribute> CarAttributeSelection(
    title: String,
    items: List<T>,
    selectedItem: T?,
    onItemSelected: (T) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            color = darkBluePrimary,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn {
            items(items) { item ->
                CarAttributeItem(
                    attribute = item,
                    isSelected = item == selectedItem,
                    onItemSelected = { onItemSelected(item) }
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun <T : CarAttribute> CarAttributeItem(
    attribute: T,
    isSelected: Boolean,
    onItemSelected: () -> Unit
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val density = LocalDensity.current.density

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = MaterialTheme.shapes.medium,
        //elevation = if (isSelected) 8.dp else 2.dp // Add elevation parameter
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemSelected() }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = attribute.name,
                fontSize = 16.sp,
                color = darkBluePrimary,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )

            Text(
                text = attribute.details,
                fontSize = 14.sp,
                color = darkBluePrimary,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )

            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

data class Engine(override val name: String, override val details: String) : CarAttribute
data class Wheel(override val name: String, val size: Int) : CarAttribute {
    override val details: String
        get() = "Size: $size"
}

interface CarAttribute {
    val name: String
    val details: String
}

data class Car(val name: String, val engine: Engine, val wheel: Wheel)
