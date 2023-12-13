package com.example.appdev2_assignment2.CRUD

import com.example.appdev2_assignment2.AppUser
import com.example.appdev2_assignment2.Car
import com.example.appdev2_assignment2.CarPart
import com.example.appdev2_assignment2.PartType
import com.example.appdev2_assignment2.User
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

// Repository for Car data handling in Firestore
class CarRepositoryFirestore(val db: FirebaseFirestore): CarRepository{

    val dbCars: CollectionReference = db.collection("cars")

    // Add car to Firestore
    override suspend fun addCar(car: Car) {
        dbCars.document(car.name).set(car)
        .addOnSuccessListener { println("Car saved") }
        .addOnFailureListener { e -> println("Error saving car: $e") }
    }

    // Get user-specific cars from Firestore
    override suspend fun getCars(user: String): Flow<List<Car>> = callbackFlow{
        val subscription = dbCars
            .whereEqualTo("userEmail", user)
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

    // Get all cars from Firestore
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

    // Get a specific car from Firestore
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

    // Delete a car from Firestore
    override suspend fun delete(car: Car) {
        dbCars.document(car.name)
            .delete()
            .addOnSuccessListener { println("Car ${car.name} successfully deleted") }
            .addOnFailureListener { error -> println("Error deleting car ${car.name}: $error") }
    }
}

//Convert Firestore DocumentSnapshot to Car object
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
            image = (partMap["image"] as? String)?: "",
            modelNum = (partMap["modelNum"] as? Long)?.toInt() ?: 0,
            description = partMap["description"] as? String ?: "",
            type = PartType.valueOf(partMap["type"] as? String ?: "Body"),
            price = (partMap["price"] as? Long) ?: 0
        )
        partsList.add(part)
    }

    return Car(user, name, partsList, vin)
}

//Defining CarRepository methods
interface CarRepository{
    suspend fun addCar(car: Car)
    suspend fun getCars(user: String): Flow<List<Car>>
    suspend fun getCars(): Flow<List<Car>>
    suspend fun getCar(name: String): Flow<Car>

    suspend fun delete(car: Car)
}