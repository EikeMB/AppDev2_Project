package com.example.appdev2_assignment2.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SummaryPage(auth: FirebaseAuth, navController: NavController, carViewModel: CarViewModel, partViewModel: CarPartViewModel, userViewModel: UserViewModel, defaultCar: MutableState<Car?>) {
    LazyColumn  {
        item {

        PartSummary(part = PartType.Body, defaultCar = defaultCar.value!!)
        PartSummary(part = PartType.Engine, defaultCar = defaultCar.value!!)
        PartSummary(part = PartType.Wheels, defaultCar = defaultCar.value!!)
        PartSummary(part = PartType.Aerodynamics, defaultCar = defaultCar.value!!)
        PartSummary(part = PartType.Interior, defaultCar = defaultCar.value!!)
        PartSummary(part = PartType.Accessories, defaultCar = defaultCar.value!!)
        }
        item {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Button(
                onClick = {
                    navController.navigate("CreateScreenRoute")
                },
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text("Modify")
            }

            Spacer(modifier = Modifier.weight(1f))

            val carParts = defaultCar.value?.parts ?: emptyList()
            val totalPrice = carParts.sumOf { it.price }
            Text(
                text = "Total Price: $totalPrice$",
                modifier = Modifier.padding(20.dp)
            )

            }
        }
    }

}


@Composable
fun PartSummary(
    part: PartType,
    defaultCar: Car,
) {
    var partChosen = defaultCar.parts.find {  it.type == part }
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
            // Image
            Image(
                painter = painterResource(id = R.drawable.artura), // Replace with your image resource
                contentDescription = "Image",
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

        // Middle: Description
        Column(
            modifier = Modifier
                .weight(2f) // Adjust weight to occupy more space
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
            text = partChosen!!.price.toString(),
            modifier = Modifier
                .weight(0.5f)
        )
    }

    Divider(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        color = Color.Black // Adjust the color as needed
    )
}

//@Preview
//@Composable
//fun SummaryPagePreview() {
//    // This is where you'll call your SummaryPage Composable
//    SummaryPage()
//}