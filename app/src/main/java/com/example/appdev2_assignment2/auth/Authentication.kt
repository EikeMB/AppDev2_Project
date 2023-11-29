package com.example.appdev2_assignment2.auth

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import com.example.appdev2_assignment2.AppUser
import com.example.appdev2_assignment2.User
import com.example.appdev2_assignment2.ViewModels.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.runBlocking


fun signIn(auth: FirebaseAuth, userName: String, password: String, navController: NavController, userViewModel: UserViewModel, error: MutableState<String?>){
    auth.signInWithEmailAndPassword(userName, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {

                runBlocking {
                    userViewModel.getUser(userName)
                }

                navController.navigate("MainScreenRoute")
            } else {
                error.value = "Sign in failed."
            }
        }
}

fun signUp(auth: FirebaseAuth, user: AppUser, password: String, navController: NavController, userViewModel: UserViewModel, error: MutableState<String?>){
    auth.createUserWithEmailAndPassword(user.email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {

                userViewModel.addUser(user)

                runBlocking { userViewModel.getUser(user.email) }

                navController.navigate("MainScreenRoute")
            } else {
                error.value = "Could not sign up! Please try again."
            }
        }
}

fun signOut(auth: FirebaseAuth, navController: NavController){
    auth.signOut()

    navController.navigate("SignInScreenRoute")
}