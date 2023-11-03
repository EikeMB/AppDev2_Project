package com.example.appdev2_assignment2

open class CarPart(var modelNum: Int, var description: String) {


    lateinit var combatibilityList: List<CarPart>
}

var parts: List<CarPart> = listOf(
    Body("R34", 0, 0, "Body of a nissan skyline r34"),
    Engine("V12", 0, 1, "V12 Engine"),

)