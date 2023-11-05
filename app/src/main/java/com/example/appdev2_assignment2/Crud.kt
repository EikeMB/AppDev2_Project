package com.example.appdev2_assignment2

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


val db = Firebase.firestore


fun seed(){
    db.collection("carParts")
        .add(getAllParts())

    db.collection("cars")
        .add(getAllCars())
}

fun getAllCars():List<Car>{
    return cars;
}

fun getCarVin(vin: Int): Car? {
    for(car in cars){
        if(car.vin == vin){
            return car;
        }
    }

    return null;
}


fun getAllParts():List<CarPart>{
    val parts: MutableList<CarPart> = mutableListOf()

    for(part in parts1){
        parts.add(part)
    }
    for(part in parts2){
        parts.add(part)
    }
    for(part in parts3){
        parts.add(part)
    }

    return parts
}

fun getPartModelNum(modelNum:Int):CarPart?{
    for(part in getAllParts()){
        if(part.modelNum == modelNum){
            return part
        }
    }

    return null;
}