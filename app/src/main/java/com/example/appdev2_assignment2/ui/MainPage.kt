package com.example.appdev2_assignment2.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appdev2_assignment2.AppUser
import com.example.appdev2_assignment2.Car
import com.example.appdev2_assignment2.PartType
import com.example.appdev2_assignment2.User
import com.example.appdev2_assignment2.ViewModels.CarPartViewModel
import com.example.appdev2_assignment2.ViewModels.CarViewModel
import com.example.appdev2_assignment2.ViewModels.UserViewModel
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomePage(auth: FirebaseAuth, navController: NavController, carViewModel: CarViewModel, partViewModel: CarPartViewModel, userViewModel: UserViewModel) {

    val user by userViewModel.activeUser.collectAsState(initial = AppUser("","",0,""))



    LaunchedEffect(Unit){
        carViewModel.getCarsForUser(user)

    }
    LaunchedEffect(Unit){
        carViewModel.getAllCars()
    }




    val cars by carViewModel.userCars.collectAsState(initial = emptyList())
    val allCars by carViewModel.allCars.collectAsState(initial = emptyList())

    Column (
        modifier = Modifier
            .fillMaxSize()
    ){


        Text(text = "Your customized cars: ", Modifier.padding(10.dp))
        Box{
            UserCarsList(cars = cars)
        }
        Text(text = "See other customized car: ", Modifier.padding(10.dp))
        UserCarsListVertical(cars = allCars)
    }
}

@Composable
fun UserCarsListVertical(cars: List<Car>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
            .background(Color(0xFFADD8E6))
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(cars) { index, car ->
                if (index % 2 != 0) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        CarCard(car = car)
                        CarCard(car = cars[index - 1])
                    }
                }
                else{
                    val isLastItem = index == cars.lastIndex
                    if (isLastItem) {
                        CarCard(car = car)
                    }
                }
            }
        }
    }
}

@Composable
fun UserCarsList(cars: List<Car>){
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Blue))
    {
        items(cars) { car ->
            CarCard(car = car)
        }
    }
}

@Composable
fun CarCard(car: Car){
    var image: Int = 0

    for(part in car.parts){
        if(part.type == PartType.Body){
            image = part.image
        }
    }




    Card(
        modifier = Modifier
            .padding(15.dp)
            .height(230.dp)
            .width(170.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp, vertical = 5.dp)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth() // Image occupies full width available
                    .height(150.dp) // Adjust the height as per your preference
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = car.name)
            // Text(text = "VIN: ${car.vin}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
