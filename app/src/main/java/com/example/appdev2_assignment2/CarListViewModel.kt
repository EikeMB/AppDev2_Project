package com.example.appdev2_assignment2

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

data class FirestoreCar(
    val name: String = "",
    val vin: Long = 0,
    val parts: List<FirestoreCarPart> = emptyList()
)

data class FirestoreCarPart(
    val name: String = "",
    val image: Int = 0,
    val modelNum: Int = 0,
    val description: String = "",
    val type: String = ""
)
class CarListViewModel: ViewModel() {

    var cars by mutableStateOf<List<Car>>(emptyList())

    init {
        loadCarsFromFireStore()
    }

    private fun loadCarsFromFireStore(){
        val firestore = FirebaseFirestore.getInstance()
        val carCollection = firestore.collection("cars")

        carCollection.get()
            .addOnSuccessListener { documents ->
                val carList = documents.map { document ->
                    val firestoreCar = document.toObject(FirestoreCar::class.java)
                    val parts = firestoreCar?.parts?.map {
                        FirestoreCarPart(it.name, it.image, it.modelNum, it.description, it.type)
                    } ?: emptyList()

                    Car(firestoreCar?.name ?: "", parts, firestoreCar?.vin?.toInt() ?: 0)
                }

                cars = carList
            }
            .addOnFailureListener { e ->
                // Handle Firestore query failure
                Log.e("TAG", "Firestore query failed: $e")
            }
    }
}