package com.example.appdev2_assignment2.auth

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.appdev2_assignment2.AppUser
import com.example.appdev2_assignment2.User
import com.example.appdev2_assignment2.ViewModels.UserViewModel
import com.google.firebase.auth.FirebaseAuth


fun signIn(auth: FirebaseAuth, email: String, password: String, navController: NavController){
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {

                val user = auth.currentUser?.email?.let { User(it) }

                navController.navigate("MainScreenRoute")
            } else {
                navController.navigate("SignUpScreenRoute")
            }
        }
}

fun signUp(auth: FirebaseAuth, email: String, password: String, navController: NavController){
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser?.email?.let { User(it) }

                navController.navigate("MainScreenRoute")
            } else {
                navController.navigate("SignUpScreenRoute")
            }
        }
}

fun signOut(auth: FirebaseAuth, navController: NavController){
    auth.signOut()

    navController.navigate("SignInScreenRoute")
}