package com.example.appdev2_assignment2.CRUD

import com.example.appdev2_assignment2.Car
import com.example.appdev2_assignment2.CarPart
import com.example.appdev2_assignment2.PartType
import com.example.appdev2_assignment2.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

suspend fun getCarsFromUser(user: User): List<Car> = withContext(Dispatchers.IO){

    val cars = mutableListOf<Car>()

    try {
        val db = FirebaseFirestore.getInstance()

        val collection = db.collection("cars")

        val querySnapshot = collection.whereEqualTo("user", user.email).get().await()

        for (document in querySnapshot.documents){
            val user = document.getString("user") ?: ""
            val name = document.getString("name") ?: ""
            val vin = document.getLong("vin")?.toInt() ?: 0

            val partsList = mutableListOf<CarPart>()
            val partsArray = document.get("parts") as? List<Map<String, Any>> ?: emptyList()

            for (partMap in partsArray) {
                val part = CarPart(
                    name = partMap["name"] as? String ?: "",
                    image = (partMap["image"] as? Long)?.toInt() ?: 0,
                    modelNum = (partMap["modelNum"] as? Long)?.toInt() ?: 0,
                    description = partMap["description"] as? String ?: "",
                    type = PartType.valueOf(partMap["type"] as? String ?: "Body")
                )
                partsList.add(part)
            }

            val car = Car(user, name, partsList, vin)
            cars.add(car)
        }
    }catch (exception: Exception){
        println(exception.message)
    }



    return@withContext cars
}


suspend fun getAllCars(): List<Car> = withContext(Dispatchers.IO){

    val cars = mutableListOf<Car>()

    try {
        val db = FirebaseFirestore.getInstance()

        val collection = db.collection("cars")

        val querySnapshot = collection.get().await()

        for (document in querySnapshot.documents){
            val user = document.getString("user") ?: ""
            val name = document.getString("name") ?: ""
            val vin = document.getLong("vin")?.toInt() ?: 0

            val partsList = mutableListOf<CarPart>()
            val partsArray = document.get("parts") as? List<Map<String, Any>> ?: emptyList()

            for (partMap in partsArray) {
                val part = CarPart(
                    name = partMap["name"] as? String ?: "",
                    image = (partMap["image"] as? Long)?.toInt() ?: 0,
                    modelNum = (partMap["modelNum"] as? Long)?.toInt() ?: 0,
                    description = partMap["description"] as? String ?: "",
                    type = PartType.valueOf(partMap["type"] as? String ?: "Body")
                )
                partsList.add(part)
            }

            val car = Car(user, name, partsList, vin)
            cars.add(car)
        }
    }catch (exception: Exception){
        println(exception.message)
    }



    return@withContext cars
}

suspend fun addCar(carToAdd: Car){
    try {
        val db = FirebaseFirestore.getInstance()

        val collection = db.collection("cars")

        val car = hashMapOf(
            "user" to carToAdd.userEmail,
            "name" to carToAdd.name,
            "parts" to carToAdd.parts.map { part ->
                mapOf(
                    "name" to part.name,
                    "image" to part.image,
                    "modelNum" to part.modelNum,
                    "description" to part.description,
                    "type" to part.type
                )
            },
            "vin" to carToAdd.vin
        )


        collection.document(carToAdd.name).set(car).await()
    }catch (e: Exception){

    }
}

suspend fun deleteCar(carToDelete: Car){
    try {
        val db = FirebaseFirestore.getInstance()

        val collection = db.collection("cars")

        val document = collection.document(carToDelete.name)

        document.delete().await()
    }catch (e: Exception){

    }
}