package com.example.appdev2_assignment2.CRUD

import com.example.appdev2_assignment2.Car
import com.example.appdev2_assignment2.CarPart
import com.example.appdev2_assignment2.PartType
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


suspend fun addPart(part: CarPart){
    try {
        val db = FirebaseFirestore.getInstance()

        val collection = db.collection("parts")

        val partToAdd = hashMapOf(
            "name" to part.name,
            "image" to part.image,
            "modelNum" to part.modelNum,
            "description" to part.description,
            "type" to part.type
        )

        collection.document(part.name).set(partToAdd).await()
    }catch(e: Exception){

    }
}

suspend fun getAllParts(): List<CarPart> = withContext(Dispatchers.IO){
    val parts = mutableListOf<CarPart>()

    try {
        val db = FirebaseFirestore.getInstance()

        val collection = db.collection("parts")

        val querySnapshot = collection.get().await()

        for (document in querySnapshot.documents) {
            val name = document.getString("name") ?: ""
            val image = document.getLong("image")?.toInt() ?: 0
            val modelNum = document.getLong("modelNum")?.toInt() ?: 0
            val description = document.getString("description") ?: ""
            val type = PartType.valueOf(document.get("type") as String)

            val part = CarPart(name, image, modelNum, description, type)

            parts.add(part)
        }
    }catch(e:Exception){}

    return@withContext(parts)
}