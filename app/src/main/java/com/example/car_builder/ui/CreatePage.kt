package com.example.car_builder.ui

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.RadioButtonDefaults
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.car_builder.AppUser
import com.example.car_builder.Car
import com.example.car_builder.CarPart
import com.example.car_builder.PartType
import com.example.car_builder.R
import com.example.car_builder.ViewModels.CarPartViewModel
import com.example.car_builder.ViewModels.CarViewModel
import com.example.car_builder.ViewModels.UserViewModel
import kotlinx.coroutines.launch
import androidx.compose.foundation.background



/**
 * Allows users to select car parts and configure a car.
 */
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("StateFlowValueCalledInComposition", "CoroutineCreationDuringComposition")
@Composable
fun CreatePage(navController: NavController, carViewModel: CarViewModel, partViewModel: CarPartViewModel, userViewModel: UserViewModel, defaultCar: MutableState<Car?>) {

    // Retrieve user data
    val user by userViewModel.activeUser.collectAsState(initial = AppUser("","",0,""))
    val coroutineScope = rememberCoroutineScope()

    // Fetch all car parts
    coroutineScope.launch { kotlinx.coroutines.coroutineScope { launch { partViewModel.getAllParts() } } }

    // Get lists of car parts and initialize selected options
    val partList by partViewModel.allParts.collectAsState(initial = emptyList())
    var nameError by rememberSaveable{ mutableStateOf<String?>(null)}

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
    else{ CarPart("", "", -1, "", PartType.Wheels, 1) }

    body = if(partList.isNotEmpty()){ partList[indexOfBodyPart] }
    else{ CarPart("", "", -1, "", PartType.Body, 1) }

    aerodynamic = if(partList.isNotEmpty()){ partList[indexOfAerodynamicPart] }
    else{ CarPart("", "", -1, "", PartType.Aerodynamics, 1) }

    engine = if(partList.isNotEmpty()){ partList[indexOfEnginePart] }
    else{ CarPart("", "", -1, "", PartType.Engine, 1) }

    interior = if(partList.isNotEmpty()){ partList[indexOfInteriorPart] }
    else{ CarPart("", "", -1, "", PartType.Interior, 1) }

    accessories = if(partList.isNotEmpty()){ partList[indexOfAccessoriesPart] }
    else{ CarPart("", "", -1, "", PartType.Accessories, 1) }

    val (wheelSelectedOption, wheelOnOptionSelected) = remember { mutableStateOf(wheel) }
    val (bodySelectedOption, bodyOnOptionSelected) = remember { mutableStateOf(body) }
    val (aerodynamicSelectedOption, aerodynamicOnOptionSelected) = remember { mutableStateOf(aerodynamic) }
    val (engineSelectedOption, engineOnOptionSelected) = remember { mutableStateOf(engine) }
    val (interiorSelectedOption, interiorOnOptionSelected) = remember { mutableStateOf(interior) }
    val (accessoriesSelectedOption, accessoriesOnOptionSelected) = remember { mutableStateOf(accessories) }


    var carName by remember { mutableStateOf("") }

    if(defaultCar.value != null){
        carName =  defaultCar.value!!.name
        for(part in defaultCar.value!!.parts){
            if(part.type == PartType.Accessories){
                accessoriesOnOptionSelected(part)
            }
            else if(part.type == PartType.Aerodynamics){
                aerodynamicOnOptionSelected(part)
            }
            else if(part.type == PartType.Body){
                bodyOnOptionSelected(part)
            }
            else if(part.type == PartType.Engine){
                engineOnOptionSelected(part)
            }
            else if(part.type == PartType.Interior){
                interiorOnOptionSelected(part)
            }
            else if(part.type == PartType.Wheels){
                wheelOnOptionSelected(part)
            }
        }
    }
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
                    .padding(8.dp).weight(1f)
            ) {
                Text("Back")
            }

            TextField(
                value = carName,
                onValueChange = { newText ->
                    carName = newText
                },
                placeholder = { Text("Car Name") },
                modifier = Modifier
                    .padding(8.dp)
                    .height(50.dp)
                    .width(150.dp)
                    .weight(1f)
            )

            Button(
                onClick = {
                    nameError = null
                    if(carName.isNotEmpty()){
                        val tempCar = Car(user.email, carName, listOf<CarPart>(bodySelectedOption, wheelSelectedOption , aerodynamicSelectedOption,
                            accessoriesSelectedOption, interiorSelectedOption, engineSelectedOption), 0)
                        carViewModel.addCar(tempCar)
                        defaultCar.value = tempCar
                        navController.navigate("SummaryScreenRoute")
                    }
                    else{
                        nameError = "Car name is required"
                    }
                },
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .weight(1f)
            ) {
                Text("Finish")
            }

        }
        nameError?.let { error ->
            Text(text = error, color = Color.Red, modifier = Modifier.padding(4.dp))
        }

        //partSection(partsList = wheelsList, selectedOption = wheelSelectedOption, onOptionSelected = wheelOnOptionSelected)
        partSection(partsList = partList,partChosenName = "Wheels", partChosen = PartType.Wheels, selectedOption = wheelSelectedOption, onOptionSelected = wheelOnOptionSelected, defaultCar = defaultCar)
        partSection(partsList = partList,partChosenName = "Body", partChosen = PartType.Body, selectedOption = bodySelectedOption, onOptionSelected = bodyOnOptionSelected,defaultCar = defaultCar)
        partSection(partsList = partList,partChosenName = "Aerodynamic", partChosen = PartType.Aerodynamics, selectedOption = aerodynamicSelectedOption, onOptionSelected = aerodynamicOnOptionSelected,defaultCar = defaultCar)
        partSection(partsList = partList,partChosenName = "Engine", partChosen = PartType.Engine, selectedOption = engineSelectedOption, onOptionSelected = engineOnOptionSelected,defaultCar = defaultCar)
        partSection(partsList = partList,partChosenName = "Interior", partChosen = PartType.Interior, selectedOption = interiorSelectedOption, onOptionSelected = interiorOnOptionSelected,defaultCar = defaultCar)
        partSection(partsList = partList,partChosenName = "Accessories", partChosen = PartType.Accessories, selectedOption = accessoriesSelectedOption, onOptionSelected = accessoriesOnOptionSelected,defaultCar = defaultCar)

    }

}


/**
 * Display a dropdown section for specific car part.
 */
@Composable
private fun partSection(partsList: List<CarPart>, partChosenName: String, partChosen: PartType, selectedOption: CarPart, onOptionSelected: (CarPart) -> Unit, defaultCar: MutableState<Car?>){
    val expanded = remember { mutableStateOf(false) }

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable { expanded.value = !expanded.value }
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
                            onOptionSelected = onOptionSelected,
                            defaultCar =  defaultCar

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


/**
 * Composable function to display detailed information of specific car part.
 */
@Composable
private fun PartInfo(
    part: CarPart,
    selectedOption: CarPart,
    onOptionSelected: (CarPart) -> Unit,
    defaultCar: MutableState<Car?>
) {
    val expanded = remember { mutableStateOf(false) }

    Surface(
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable { expanded.value = !expanded.value }
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
        ) {
            Row(modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
            ){
                if(selectedOption.modelNum == -1){
                    onOptionSelected(part)
                }
                RadioButton(
                    selected = (part == selectedOption),
                    onClick = { onOptionSelected(part)
                              defaultCar.value = null},
                    modifier = Modifier.padding(end = 16.dp),
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color.White,
                        unselectedColor = MaterialTheme.colorScheme.onSecondary
                    )
                )
                Text(text = part.name)
            }
            if (expanded.value) {
                Text( text = part.description,
                    modifier = Modifier.padding(8.dp))
                Text( text = "Cost: ${part.price}$",
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
        IconButton(
            onClick = {
                expanded.value = !expanded.value
            }
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
