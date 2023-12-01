package com.example.appdev2_assignment2


data class CarPart(var name:String, var image:Int, var modelNum: Int, var description: String, var type: PartType, var price: Long) {

}

enum class PartType {
    Body, Engine, Wheels, Aerodynamics, Interior, Accessories
}





