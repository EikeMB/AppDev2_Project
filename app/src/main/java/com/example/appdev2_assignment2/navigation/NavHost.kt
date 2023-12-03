package com.example.appdev2_assignment2.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appdev2_assignment2.AppUser
import com.example.appdev2_assignment2.Car
import com.example.appdev2_assignment2.StartupPage
import com.example.appdev2_assignment2.User
import com.example.appdev2_assignment2.ViewModels.CarPartViewModel
import com.example.appdev2_assignment2.ViewModels.CarViewModel
import com.example.appdev2_assignment2.ViewModels.UserViewModel
import com.example.appdev2_assignment2.ui.About
import com.example.appdev2_assignment2.ui.CommonScaffold
import com.example.appdev2_assignment2.ui.HomePage
import com.example.appdev2_assignment2.ui.LoginScreen
import com.example.appdev2_assignment2.ui.Page2
import com.example.appdev2_assignment2.ui.SignUpScreen
import com.example.appdev2_assignment2.ui.UserProfilePage
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


fun checkSignIn(auth: FirebaseAuth): Boolean{
    val currentUser = auth.currentUser
    return currentUser != null
}

@Composable
fun MainNavHost(navController: NavHostController, auth: FirebaseAuth, userViewModel: UserViewModel, carViewModel: CarViewModel, partViewModel: CarPartViewModel, defaultCar: MutableState<Car?>){
    Log.d("authfirst", auth.currentUser?.displayName.toString())

    NavHost(navController = navController, startDestination = "LoginScreenRoute") {
        composable("LoginScreenRoute") {
            if(!checkSignIn(auth)){
                LoginScreen(navController, auth = auth, userViewModel = userViewModel)
            }
            else{
                navController.navigate("MainScreenRoute")
            }
        }
        composable("SignUpScreenRoute") {
           if(!checkSignIn(auth)){
               SignUpScreen(navController, auth = auth, userViewModel = userViewModel)
           }
           else{
               navController.navigate("MainScreenRoute")
           }
        }
        composable("MainScreenRoute") {
            if(auth.currentUser != null){
                val userEmail = auth.currentUser!!.email
                LaunchedEffect(Unit){
                    launch{ userViewModel.getUser(userEmail!!) }
                    launch { carViewModel.getCarsForUser(userEmail!!) }
                    launch { carViewModel.getAllCars() }
                }
                CommonScaffold(navController = navController, userViewModel = userViewModel) {
                    HomePage(auth, navController, carViewModel, partViewModel, userViewModel, defaultCar)
                }
            }
            else{
                navController.navigate("LoginScreenRoute")
            }
        }
        composable("CreateScreenRoute") {
            if(auth.currentUser != null){
                CommonScaffold(navController = navController, userViewModel = userViewModel) {
                    Page2(auth, navController, carViewModel, partViewModel, userViewModel, defaultCar)
                }
            }
            else{
                navController.navigate("LoginScreenRoute")
            }
        }
        composable("AboutScreenRoute") {
            if(auth.currentUser != null){
                CommonScaffold(navController = navController, userViewModel = userViewModel) {
                    About(auth, navController)
                }
            }
            else{
                navController.navigate("LoginScreenRoute")
            }
        }
        composable("UserProfileRoute") {
            if(auth.currentUser != null){
                val user = auth.currentUser?.email?.let { User(it) }
                LaunchedEffect(Unit){
                    userViewModel.getUser(user!!.email)
                }

                val appUser by userViewModel.activeUser.collectAsState(initial = AppUser("", "", 0,""))

                if (appUser != null) {
                    CommonScaffold(navController = navController, userViewModel = userViewModel) {
                        UserProfilePage(
                            userViewModel = userViewModel,
                            onProfilePictureChange = { /* implement the logic */ },
                            onNameChange = { /* implement the logic */ },
                            onAgeChange = { /* implement the logic */ },
                            onPasswordChange = { /* implement the logic */ },
                            onApplyChanges = { /* implement the logic */ },
                            navController = navController,
                            auth = auth,
                            carViewModel = carViewModel
                        )
                    }
                }
            }
            else{
                navController.navigate("LoginScreenRoute")
            }
        }
    }
}
