package com.example.appdev2_assignment2.CRUD

import com.example.appdev2_assignment2.AppUser
import com.example.appdev2_assignment2.Car
import com.example.appdev2_assignment2.CarPart
import com.example.appdev2_assignment2.PartType
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class CarRepositoryFirestore(val db: FirebaseFirestore): CarRepository{

    val dbCars: CollectionReference = db.collection("cars")

    override suspend fun addCar(car: com.example.appdev2_assignment2.ui.Car) {
        dbCars.document(car.name).set(car)
            .addOnSuccessListener {
                println("Car saved")
            }
            .addOnFailureListener { e ->
                println("Error saving car: $e")
            }
    }

    override suspend fun getCars(user: AppUser): Flow<List<Car>> = callbackFlow{
        val subscription = dbCars
            .whereEqualTo("userEmail", user.email)
            .addSnapshotListener{ snapshot, error ->
            if(error != null){
                println("Listen failed: $error")
                return@addSnapshotListener
            }
            if(snapshot != null){
                var cars: MutableList<Car> = mutableListOf()
                for(document in snapshot.documents){


                    val car = convertSnapshotToCar(document)
                    cars.add(car)
                }
                if(cars != null){
                    println("Real-time update to car")
                    trySend(cars)
                }else{
                    println("Cars has become null")
                    trySend(listOf<Car>())
                }
            }else {
                println("Cars collection does not exit")
                trySend(listOf<Car>())
            }
        }
        awaitClose{ subscription.remove() }
    }


    override suspend fun getCars(): Flow<List<Car>> = callbackFlow{
        val subscription = dbCars
            .addSnapshotListener{ snapshot, error ->
                if(error != null){
                    println("Listen failed: $error")
                    return@addSnapshotListener
                }
                if(snapshot != null){
                        var cars: MutableList<Car> = mutableListOf()
                        for (document in snapshot.documents) {

                            val car = convertSnapshotToCar(document)
                            cars.add(car)
                        }
                    if(cars != null){
                        println("Real-time update to car")
                        trySend(cars)
                    }else{
                        println("Cars has become null")
                        trySend(listOf<Car>())
                    }
                }else {
                    println("Cars collection does not exit")
                    trySend(listOf<Car>())
                }
            }
        awaitClose{ subscription.remove() }
    }

    override suspend fun getCar(name: String): Flow<Car> = callbackFlow{
        val docRef = dbCars.document(name)
        val subscription = docRef.addSnapshotListener { snapshot, error ->
            if(error != null){
                println("Listen failed: $error")
                return@addSnapshotListener
            }
            if(snapshot != null && snapshot.exists()){
                val car = convertSnapshotToCar(snapshot)
                if(car != null){
                    println("Real-time update to part")
                    trySend(car)
                }else{
                    println("Part has becom null")
                    trySend(Car("", "", listOf(), 0))
                }
            }else{
                println("Part does not exist")
                trySend(Car("", "", listOf(), 0))
            }
        }
        awaitClose { subscription.remove() }
    }

    override suspend fun delete(car: Car) {
        dbCars.document(car.name)
            .delete()
            .addOnSuccessListener { println("Car ${car.name} successfully deleted") }
            .addOnFailureListener { error -> println("Error deleting car ${car.name}: $error") }
    }
}

fun convertSnapshotToCar(document: DocumentSnapshot): Car{
    val user = document.getString("userEmail") ?: ""
    val name = document.getString("name") ?: ""
    val vin = document.getLong("vin")?.toInt() ?: 0

    val partsList = mutableListOf<CarPart>()
    val partsArray =
        document.get("parts") as? List<Map<String, Any>> ?: emptyList()

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

    return Car(user, name, partsList, vin)
}

interface CarRepository{
    suspend fun addCar(car: com.example.appdev2_assignment2.ui.Car)
    suspend fun getCars(user: AppUser): Flow<List<Car>>
    suspend fun getCars(): Flow<List<Car>>
    suspend fun getCar(name: String): Flow<Car>

    suspend fun delete(car: Car)
}