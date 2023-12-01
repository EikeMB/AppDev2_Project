package com.example.appdev2_assignment2.ui

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appdev2_assignment2.Car
import com.example.appdev2_assignment2.CarPart
import com.example.appdev2_assignment2.PartType
import com.example.appdev2_assignment2.R
import com.example.appdev2_assignment2.ViewModels.CarPartViewModel
import com.example.appdev2_assignment2.ViewModels.CarViewModel
import com.example.appdev2_assignment2.ViewModels.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@Composable
private fun partSection(partsList: List<CarPart>, partChosenName: String, partChosen: PartType, selectedOption: CarPart, onOptionSelected: (CarPart) -> Unit){
    val expanded = remember { mutableStateOf(false) }

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp),
                //verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = partChosenName)
                }
                if (expanded.value) {

                    partsList.filter { it.type == partChosen }.forEach { part ->
                        PartInfo(
                            part = part,
                            selectedOption = selectedOption,
                            onOptionSelected = onOptionSelected
                        )
                    }

                }
            }
            IconButton(
                onClick = { expanded.value = !expanded.value },
            ) {
                Icon(
                    imageVector = if (expanded.value) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expanded.value) {
                        stringResource(R.string.show_less)
                    } else {
                        stringResource(R.string.show_more)
                    }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("StateFlowValueCalledInComposition", "CoroutineCreationDuringComposition")
@Composable
fun Page2(auth: FirebaseAuth, navController: NavController, carViewModel: CarViewModel, partViewModel: CarPartViewModel, userViewModel: UserViewModel, defaultCar: MutableState<Car?>) {

    val coroutineScope = rememberCoroutineScope()

    coroutineScope.launch {
        kotlinx.coroutines.coroutineScope {
            launch { partViewModel.getAllParts() }
        }
    }

    val partList by partViewModel.allParts.collectAsState(initial = emptyList())

    var wheel: CarPart
    var body: CarPart
    var aerodynamic: CarPart
    var engine: CarPart
    var interior: CarPart
    var accessories: CarPart


    val indexOfWheelPart = partList.indexOfFirst { it.type == PartType.Wheels }
    val indexOfBodyPart = partList.indexOfFirst { it.type == PartType.Body }
    val indexOfAerodynamicPart = partList.indexOfFirst { it.type == PartType.Aerodynamics }
    val indexOfEnginePart = partList.indexOfFirst { it.type == PartType.Engine }
    val indexOfInteriorPart = partList.indexOfFirst { it.type == PartType.Interior }
    val indexOfAccessoriesPart = partList.indexOfFirst { it.type == PartType.Accessories }

    wheel = if(partList.isNotEmpty()){ partList[indexOfWheelPart] }
    else{ CarPart("", 0, -1, "", PartType.Wheels) }

    body = if(partList.isNotEmpty()){ partList[indexOfBodyPart] }
    else{ CarPart("", 0, -1, "", PartType.Body) }

    aerodynamic = if(partList.isNotEmpty()){ partList[indexOfAerodynamicPart] }
    else{ CarPart("", 0, -1, "", PartType.Aerodynamics) }

    engine = if(partList.isNotEmpty()){ partList[indexOfEnginePart] }
    else{ CarPart("", 0, -1, "", PartType.Engine) }

    interior = if(partList.isNotEmpty()){ partList[indexOfInteriorPart] }
    else{ CarPart("", 0, -1, "", PartType.Interior) }

    accessories = if(partList.isNotEmpty()){ partList[indexOfAccessoriesPart] }
    else{ CarPart("", 0, -1, "", PartType.Accessories) }

    val (wheelSelectedOption, wheelOnOptionSelected) = remember { mutableStateOf(wheel) }
    val (bodySelectedOption, bodyOnOptionSelected) = remember { mutableStateOf(body) }
    val (aerodynamicSelectedOption, aerodynamicOnOptionSelected) = remember { mutableStateOf(aerodynamic) }
    val (engineSelectedOption, engineOnOptionSelected) = remember { mutableStateOf(engine) }
    val (interiorSelectedOption, interiorOnOptionSelected) = remember { mutableStateOf(interior) }
    val (accessoriesSelectedOption, accessoriesOnOptionSelected) = remember { mutableStateOf(accessories) }


    val creating = true
    var carName by remember { mutableStateOf("") }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ){


        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Button(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text("Back")
            }

            TextField(
                value = carName,
                onValueChange = { newText ->
                    carName = newText
                },
                placeholder = { Text("Car Name") }, // Add the placeholder
                modifier = Modifier
                    .padding(8.dp)
                    .height(50.dp)
                    .width(150.dp)
            )

            Button(
                onClick = {
                    if (creating) {
                        navController.navigate("MainScreenRoute")
                    } else {
                        navController.navigate("UserProfileRoute")
                    }
                },
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text("Finish")
            }
        }

        //partSection(partsList = wheelsList, selectedOption = wheelSelectedOption, onOptionSelected = wheelOnOptionSelected)
        partSection(partsList = partList,partChosenName = "Wheels", partChosen = PartType.Wheels, selectedOption = wheelSelectedOption, onOptionSelected = wheelOnOptionSelected)
        partSection(partsList = partList,partChosenName = "Body", partChosen = PartType.Body, selectedOption = bodySelectedOption, onOptionSelected = bodyOnOptionSelected)
        partSection(partsList = partList,partChosenName = "Aerodynamic", partChosen = PartType.Aerodynamics, selectedOption = aerodynamicSelectedOption, onOptionSelected = aerodynamicOnOptionSelected)
        partSection(partsList = partList,partChosenName = "Engine", partChosen = PartType.Engine, selectedOption = engineSelectedOption, onOptionSelected = engineOnOptionSelected)
        partSection(partsList = partList,partChosenName = "Interior", partChosen = PartType.Interior, selectedOption = interiorSelectedOption, onOptionSelected = interiorOnOptionSelected)
        partSection(partsList = partList,partChosenName = "Accessories", partChosen = PartType.Accessories, selectedOption = accessoriesSelectedOption, onOptionSelected = accessoriesOnOptionSelected)

    }

}



@Composable
private fun PartInfo(
    part: CarPart,
    selectedOption: CarPart,
    onOptionSelected: (CarPart) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    val extraPadding = if (expanded.value) 48.dp else 0.dp

    Surface(
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            //verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
            ){
                RadioButton(
                    selected = (part == selectedOption),
                    onClick = { onOptionSelected(part) },
                    modifier = Modifier.padding(end = 16.dp)
                )
                Text(text = part.name)
            }
            if (expanded.value) {
                Text( text = part.description )
                //Text( text = part.price )----------Add price
            }
        }
        IconButton(
            onClick = { expanded.value = !expanded.value }
        ) {
            Icon(
                imageVector = if (expanded.value) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded.value) {
                    stringResource(R.string.show_less)
                }
                else { stringResource(R.string.show_more) }
            )
        }
    }
}
