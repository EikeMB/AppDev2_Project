package com.example.appdev2_assignment2.auth

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.appdev2_assignment2.AppUser
import com.example.appdev2_assignment2.Car
import com.example.appdev2_assignment2.User
import com.example.appdev2_assignment2.ViewModels.CarViewModel
import com.example.appdev2_assignment2.ViewModels.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.runBlocking

// Sign in a user using email and password
fun signIn(auth: FirebaseAuth, userName: String, password: String, navController: NavController, userViewModel: UserViewModel, error: MutableState<String?>){
    auth.signInWithEmailAndPassword(userName, password)
        .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    runBlocking {
                        userViewModel.getUser(userName)
                        navController.navigate("MainScreenRoute")
                    }

                } else {
                    val errorString = task.exception.toString()
                    error.value = errorString.substringAfterLast(":")
                }
        }
}

// Sign up a new user with email, password, and user information
fun signUp(auth: FirebaseAuth, user: AppUser, password: String, navController: NavController, userViewModel: UserViewModel, error: MutableState<String?>){
    auth.createUserWithEmailAndPassword(user.email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                userViewModel.addUser(user)
                runBlocking {
                    userViewModel.getUser(user.email)
                    navController.navigate("MainScreenRoute")
                }
            } else {
                val errorString = task.exception.toString()
                error.value = errorString.substringAfterLast(":")
            }
        }
}

// Sign out the current user
fun signOut(auth: FirebaseAuth, navController: NavController){
    try {
        auth.signOut()
        navController.navigate("LoginScreenRoute")
    }catch(e: Exception){
        println(e.message)
    }
}

fun delete(auth: FirebaseAuth, navController: NavController, userViewModel: UserViewModel, currentUser: AppUser, carViewModel: CarViewModel, cars: List<Car>){
    try {
        var user = auth.currentUser!!.delete()
            .addOnCompleteListener {
                if(it.isSuccessful){
                    userViewModel.deleteUser(currentUser)
                    for(car in cars){
                        carViewModel.deleteCar(car)
                    }
                    navController.navigate("LoginScreenRoute")
                }
            }
    }catch (e: Exception){
        println(e.message)
    }
}