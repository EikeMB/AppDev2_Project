@file:OptIn(ExperimentalGlideComposeApi::class)

package com.example.appdev2_assignment2.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.appdev2_assignment2.AppUser
import com.example.appdev2_assignment2.Car
import com.example.appdev2_assignment2.PartType
import com.example.appdev2_assignment2.User
import com.example.appdev2_assignment2.ViewModels.CarPartViewModel
import com.example.appdev2_assignment2.ViewModels.CarViewModel
import com.example.appdev2_assignment2.ViewModels.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.example.compose.darkBluePrimary
import com.example.compose.lightBluePrimary

/**
 * Composable of home page for displaying user cars.
 */
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomePage( navController: NavController, carViewModel: CarViewModel, defaultCar: MutableState<Car?>) {

    // Retrieve user's customized cars and all available cars
    val cars by carViewModel.userCars.collectAsState(initial = emptyList())
    val allCars by carViewModel.allCars.collectAsState(initial = emptyList())

    Column (modifier = Modifier.fillMaxSize()){

        // Display all user's customized cars
        if(cars.isNotEmpty()){
            Text(text = "Your customized cars: ", Modifier.padding(10.dp), color = darkBluePrimary)
            Box{ UserCarsList(cars = cars, navController = navController, defaultCar = defaultCar) }
        }
        // Display other customized cars including users
        Text(text = "See other customized car: ", Modifier.padding(10.dp), color = darkBluePrimary)
        UserCarsListVertical(cars = allCars, navController = navController, defaultCar = defaultCar)
    }
}


/**
 * Compose to display list of all the cars in a vertical arrangement.
 */
@Composable
fun UserCarsListVertical(cars: List<Car>, navController: NavController, defaultCar: MutableState<Car?>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
            .background(lightBluePrimary)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(cars) { index, car ->
                // Display cars in pairs
                if (index % 2 != 0) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        CarCard(car = car, navController = navController, defaultCar = defaultCar)
                        CarCard(car = cars[index - 1], navController = navController, defaultCar = defaultCar)
                    }
                }
                else{
                    // Display the last car if it's a single item
                    val isLastItem = index == cars.lastIndex
                    if (isLastItem) { CarCard(car = car, navController = navController, defaultCar = defaultCar) }
                }
            }
        }
    }
}


/**
 * Composable function  for displaying a row of the user's cars.
 */
@Composable
fun UserCarsList(cars: List<Car>, navController: NavController, defaultCar: MutableState<Car?>){
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(lightBluePrimary)
    ){ items(cars) { car -> CarCard(car = car, navController = navController, defaultCar = defaultCar) } }
}

/**
 * Composable function to display card representing a car.
 */
@Composable
fun CarCard(car: Car, navController: NavController, defaultCar: MutableState<Car?>){
    var image =  ""
    for(part in car.parts){ if(part.type == PartType.Body){ image = part.image } }

    Card(
        modifier = Modifier
            .padding(15.dp)
            .height(230.dp)
            .width(170.dp)
            .clickable {
                defaultCar.value = car
                navController.navigate("SummaryScreenRoute")  }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp, vertical = 5.dp)
        ) {

            GlideImage(
                model = image,
                contentDescription = "car picture",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Name: ${car.name}", color = darkBluePrimary)
            Text(text = "made by: ${car.userEmail}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
