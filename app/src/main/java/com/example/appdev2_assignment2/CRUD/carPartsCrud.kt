package com.example.appdev2_assignment2.CRUD

import com.example.appdev2_assignment2.Car
import com.example.appdev2_assignment2.CarPart
import com.example.appdev2_assignment2.PartType
import com.example.appdev2_assignment2.User
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlinx.coroutines.channels.awaitClose
import org.w3c.dom.Document


class CarPartRepositoryFirestore(val db: FirebaseFirestore): CarPartRepository{

    val dbParts = db.collection("parts")

    override suspend fun addCarPart(part: CarPart) {
        dbParts.document(part.name).set(part)
            .addOnSuccessListener {
                println("Success adding carPart")
            }
            .addOnFailureListener {e ->
                println("Failure adding carPart. Error: $e")
            }
    }

    override suspend fun getParts(): Flow<List<CarPart>>  = callbackFlow{
        val subscription = dbParts
            .addSnapshotListener { snapshot, error ->
                if(error != null){
                    println("Listen fail: $error")
                    return@addSnapshotListener
                }
                if(snapshot != null){
                    var parts: MutableList<CarPart> = mutableListOf()
                    for(document in snapshot.documents){


                        val part = convertSnapshotToCarPart(document)
                        parts.add(part)
                    }
                    if(parts != null){
                        println("Real-time update to part")
                        trySend(parts)
                    }else{
                        println("Parts has become null")
                        trySend(listOf<CarPart>())
                    }

                }else{
                    println("Parts collection does not exist")
                    trySend(listOf<CarPart>())
                }
            }
        awaitClose { subscription.remove()}
    }

    override suspend fun getPart(name: String): Flow<CarPart>  = callbackFlow{
        val docRef = dbParts.document(name)
        val subscription = docRef.addSnapshotListener { snapshot, error ->
            if(error != null){
                println("Listen failed: $error")
                return@addSnapshotListener
            }
            if(snapshot != null && snapshot.exists()){
                val part = convertSnapshotToCarPart(snapshot)
                if(part != null){
                    println("Real-time update to part")
                    trySend(part)
                }else{
                    println("Part has becom null")
                    trySend(CarPart("", "", 0, "", PartType.Body, 0))
                }
            }else{
                println("Part does not exist")
                trySend(CarPart("", "", 0, "", PartType.Body, 0))
            }
        }
        awaitClose { subscription.remove() }
    }

    override suspend fun getPartsOfType(type: PartType): Flow<List<CarPart>>  = callbackFlow{
        val subscription = dbParts
            .whereEqualTo("type", type.name)
            .addSnapshotListener { snapshot, error ->
                if(error != null){
                    println("Listen failed: $error")
                    return@addSnapshotListener
                }

                if(snapshot != null){
                    var parts: MutableList<CarPart> = mutableListOf()
                    for(document in snapshot.documents){

                        val part = convertSnapshotToCarPart(document)
                        parts.add(part)
                    }
                    if(parts != null){
                        println("Real-time update to part")
                        trySend(parts)
                    }else{
                        println("Parts has become null")
                        trySend(listOf())
                    }
                }else{
                    println("Parts collection does not exist")
                    trySend(listOf())
                }
            }
        awaitClose { subscription.remove() }
    }

    override suspend fun delete(carPart: CarPart) {
        dbParts.document(carPart.name)
            .delete()
            .addOnSuccessListener { println("Car part ${carPart.name} successfully deleted") }
            .addOnFailureListener { error -> println("Error deleting car part ${carPart.name}: $error") }
    }

}



fun convertSnapshotToCarPart(document: DocumentSnapshot): CarPart{
    val name = document.getString("name") ?: ""
    val image = document.getString("image")?: ""
    val modelNum = document.getLong("modelNum")?.toInt() ?: 0
    val description = document.getString("description") ?: ""
    val type = PartType.valueOf(document.getString("type") ?: "Body")
    val price = document.getLong("price") ?: 0

    return CarPart(name, image, modelNum, description, type, price)
}

interface CarPartRepository{

    suspend fun addCarPart(part: CarPart)
    suspend fun getParts(): Flow<List<CarPart>>
    suspend fun getPart(name: String): Flow<CarPart>
    suspend fun getPartsOfType(type: PartType): Flow<List<CarPart>>
    suspend fun delete(carPart: CarPart)
}