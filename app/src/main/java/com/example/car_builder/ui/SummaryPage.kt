@file:OptIn(ExperimentalGlideComposeApi::class)

package com.example.car_builder.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.car_builder.Car
import com.example.car_builder.PartType
import com.example.car_builder.ViewModels.CarViewModel
import com.example.car_builder.ViewModels.UserViewModel


/**
 * Composable of summary page displaying car parts summary and buttons to modify/delete
 */
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SummaryPage( navController: NavController, carViewModel: CarViewModel, userViewModel: UserViewModel, defaultCar: MutableState<Car?>) {
    val user by userViewModel.activeUser.collectAsState()
    LazyColumn  {
        item {
        PartSummary(part = PartType.Body, defaultCar = defaultCar.value)
        PartSummary(part = PartType.Engine, defaultCar = defaultCar.value)
        PartSummary(part = PartType.Wheels, defaultCar = defaultCar.value)
        PartSummary(part = PartType.Aerodynamics, defaultCar = defaultCar.value)
        PartSummary(part = PartType.Interior, defaultCar = defaultCar.value)
        PartSummary(part = PartType.Accessories, defaultCar = defaultCar.value)
        }
        item {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            if(defaultCar.value != null){
                if(defaultCar.value!!.userEmail == user.email){
                    Button(
                        onClick = {
                            navController.navigate("CreateScreenRoute")
                        },
                        modifier = Modifier
                            .padding(8.dp).weight(1f)
                    ) { Text("Modify") }
                    Button(
                        onClick = {
                            carViewModel.deleteCar(defaultCar.value!!)
                            navController.navigate("MainScreenRoute")
                        },
                        modifier = Modifier.padding(8.dp).weight(1f)
                    ) { Text("Delete") }
                }
            }

            val carParts = defaultCar.value?.parts ?: emptyList()
            val totalPrice = carParts.sumOf { it.price }
            Text(
                text = "Total Price: $totalPrice$",
                modifier = Modifier.padding(20.dp).weight(1f)
            )
            }
        }
    }
}


/**
 * Displaying summary of a specific car part.
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PartSummary(part: PartType, defaultCar: Car?)
{
    var partChosen = defaultCar?.parts?.find {  it.type == part }
    var image: String
    if(partChosen != null){ image = partChosen.image }
    else{ image = "" }
    Row(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left: Image and Text
        Column(
            modifier = Modifier
                .weight(1.6f)
                .padding(horizontal = 5.dp),
        ) {
            GlideImage(
                model = image,
                contentDescription = "part picture",
                modifier = Modifier
                    .size(50.dp)
                    .clip(shape = CircleShape)
            )
            Spacer(modifier = Modifier.height(4.dp))

            // Text
            Text(
                modifier = Modifier.padding(horizontal = 5.dp),
                text = part.toString(),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        if(partChosen != null){
            // Middle: Description
            Column(
                modifier = Modifier
                    .weight(2.5f)
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = partChosen!!.name,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Right: Price
            Text(
                text = "${partChosen!!.price}$",
                modifier = Modifier
                    .weight(0.7f)
            )
        }
    }

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        color = Color.Black
    )
}