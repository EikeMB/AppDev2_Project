package com.example.appdev2_assignment2.auth

import com.example.appdev2_assignment2.User
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {

    fun currentUser(): StateFlow<User?>

    suspend fun signUp(email:String, password:String):Boolean

    suspend fun signIn(email:String, password: String): Boolean

    fun signOut()

    suspend fun delete()
}