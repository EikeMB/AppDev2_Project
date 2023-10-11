package com.example.appdev2_assignment2

import android.media.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

//Car Class with a name an image and a company to associate it with
class Car(var name:String, var image:Int, var company:CarCompany) {
}

//Car Company class with a name and a logo image
class CarCompany(var name:String, var logo: Int){
}

//All car companies
var mercedes:CarCompany = CarCompany("Mercedes", R.drawable.mercedes)
var ferrari:CarCompany = CarCompany("Ferrari", R.drawable.ferrari)
var mcLaren:CarCompany = CarCompany("McLaren", R.drawable.mclaren)

var carCompanies:List<CarCompany> = listOf(
    mercedes,
    ferrari,
    mcLaren
)

//All Cars
var cars:List<Car> = listOf(
    Car("Amg One", R.drawable.amgone, mercedes),
    Car("Amg GT Black", R.drawable.amggtblackseries, mercedes),
    Car("G Wagon", R.drawable.gwagon, mercedes),
    Car("laFerrari", R.drawable.laferrari, ferrari),
    Car("488GTB", R.drawable.f488gtb, ferrari),
    Car("f12", R.drawable.f12, ferrari),
    Car("Senna", R.drawable.senna, mcLaren),
    Car("F1", R.drawable.f1, mcLaren),
    Car("720s", R.drawable.m720s, mcLaren),
    Car("Daytona", R.drawable.daytona, ferrari),
    Car("F8 Tributo", R.drawable.f8tributo, ferrari),
    Car("FXX-K", R.drawable.fxxk, ferrari),
    Car("Roma", R.drawable.roma, ferrari),
    Car("Portofino", R.drawable.portofino, ferrari),
    Car("A Class", R.drawable.a, mercedes),
    Car("CLA", R.drawable.cla, mercedes),
    Car("CLS", R.drawable.cls, mercedes),
    Car("EQE", R.drawable.eqe, mercedes),
    Car("Artura", R.drawable.artura, mcLaren),
    Car("P1", R.drawable.p1, mcLaren),
    Car("Speedtail", R.drawable.speedtail, mcLaren)
)