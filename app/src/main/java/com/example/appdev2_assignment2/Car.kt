package com.example.appdev2_assignment2

import android.media.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource


class Car(var name:String, var parts: List<CarPart>, var vin:Int) {

}
var cars: List<Car> = listOf(
    Car("nissan skyline r34", parts1,123),
    Car("Ferrari F430", parts2, 456),
    Car("Porsche 911 GT3", parts3, 789)
)




