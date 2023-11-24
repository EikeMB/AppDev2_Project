package com.example.appdev2_assignment2.CRUD

import com.example.appdev2_assignment2.AppUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class UserRepositoryFirestore(var db: FirebaseFirestore):UserRepository {

    val dbUsers = db.collection("users")

    override suspend fun addUser(user: AppUser) {
        dbUsers.document(user.name).set(user)
            .addOnSuccessListener {
                println("User saved")
            }
            .addOnFailureListener { error ->
                println("Error saving user: $error")
            }
    }

    override suspend fun getUsers(): Flow<List<AppUser>>  = callbackFlow{
        val subscription = dbUsers
            .addSnapshotListener { snapshot, error ->
                if(error != null){
                    println("Listen failed: $error")
                    return@addSnapshotListener
                }
                if(snapshot != null){
                    var users: MutableList<AppUser> = mutableListOf()
                    for(document in snapshot.documents){

                        var user = convertDocumentToUser(document)

                        users.add(user)
                    }
                    if(users != null){
                        println("Real-time update to user")
                        trySend(users)
                    }else{
                        println("Cars has become null")
                        trySend(listOf<AppUser>())
                    }
                }
                else{
                    println("Users collection does not exist")
                    trySend(listOf<AppUser>())
                }
            }
        awaitClose { subscription.remove()}
    }

    override suspend fun getUser(user: AppUser): Flow<AppUser>  = callbackFlow{
        val docRef = dbUsers.document(user.name)
        val subscription = docRef.addSnapshotListener { snapshot, error ->
            if(error != null){
                println("Listen failed: $error")
                return@addSnapshotListener
            }
            if(snapshot != null && snapshot.exists()){
                val user = convertDocumentToUser(snapshot)

                if(user != null){
                    println("Real-time update to user")
                    trySend(user)
                }else{
                    println("User has become null")
                    trySend(AppUser("", "", 0, 0))
                }
            }else{
                println("User does not exist")
                trySend(AppUser("", "", 0, 0))
            }
        }
        awaitClose { subscription.remove()}
    }

    override suspend fun delete(user: AppUser) {
        dbUsers.document(user.name)
            .delete()
            .addOnSuccessListener { println("User ${user.name} has been deleted") }
            .addOnFailureListener { error -> println("Error deleting user ${user.name}: $error") }
    }
}

fun convertDocumentToUser(document: DocumentSnapshot): AppUser{
    var name = document.getString("name") ?: ""
    var email = document.getString("email") ?: ""
    var age = document.getLong("age")?.toInt() ?: 0

    var picture = document.getLong("picture")?.toInt() ?: 0

    return AppUser(email, name, age, picture)
}
interface UserRepository{
    suspend fun addUser(user: AppUser)
    suspend fun getUsers(): Flow<List<AppUser>>
    suspend fun getUser(user: AppUser): Flow<AppUser>
    suspend fun delete(user: AppUser)
}