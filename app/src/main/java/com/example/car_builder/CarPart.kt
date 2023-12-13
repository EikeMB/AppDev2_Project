package com.example.car_builder

data class CarPart(var name:String, var image: String, var modelNum: Int, var description: String, var type: PartType, var price: Long) {
}
enum class PartType {
    Body, Engine, Wheels, Aerodynamics, Interior, Accessories
}

