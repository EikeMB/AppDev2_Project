package com.example.appdev2_assignment2

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore

class Crud {
    private val db = Firebase.firestore


    fun seedData(){

        val arrayParts = arrayListOf<Int>()

        for(part in parts1){
            arrayParts.add(part.modelNum)
        }

        val car = hashMapOf(
            "name" to "R34",
            "image" to 0,
            "vin" to 123,
            "compatible parts" to arrayParts
        )

        db.collection("cars")
            .document("R34")
            .set(car)

    }

    fun getAllCars(){


        var carCollection = arrayListOf<Car>()

        db.collection("cars")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    carCollection.add(Car(document.data["name"],
                        document.data["compatible parts"],
                        document.data["vin"]
                    ))
                    Log.d("TAG", "${document.id} => ${document.data}")
                }

            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents: ", exception)
            }

    }

    fun getAllCars(cars: List<Car>): List<Car>{
        return cars
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
}