package com.example.appdev2_assignment2

data class CarPart(
    val name: String,
    val image: Int,
    val modelNum: Int,
    val description: String,
    val type: CarPartType
)

enum class CarPartType {
    BODY, ENGINE, WHEELS, AERODYNAMICS, INTERIOR, ACCESSORIES
}

