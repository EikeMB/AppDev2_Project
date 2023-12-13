package com.example.car_builder.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.car_builder.AppUser
import com.example.car_builder.Car
import com.example.car_builder.User
import com.example.car_builder.ViewModels.CarPartViewModel
import com.example.car_builder.ViewModels.CarViewModel
import com.example.car_builder.ViewModels.UserViewModel
import com.example.car_builder.ui.About
import com.example.car_builder.ui.CommonScaffold
import com.example.car_builder.ui.CreatePage
import com.example.car_builder.ui.HomePage
import com.example.car_builder.ui.LoginScreen
import com.example.car_builder.ui.SignUpScreen
import com.example.car_builder.ui.SummaryPage
import com.example.car_builder.ui.UserProfilePage
import com.google.firebase.auth.FirebaseAuth
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
                CommonScaffold(navController = navController, userViewModel = userViewModel, defaultCar) {
                    HomePage( navController, carViewModel, defaultCar)
                }
            }
            else{
                navController.navigate("LoginScreenRoute")
            }
        }
        composable("CreateScreenRoute") {
            if(auth.currentUser != null){
                CommonScaffold(navController = navController, userViewModel = userViewModel, defaultCar) {
                    CreatePage(navController, carViewModel, partViewModel, userViewModel, defaultCar)
                }
            }
            else{
                navController.navigate("LoginScreenRoute")
            }
        }
        composable("SummaryScreenRoute") {
            if(auth.currentUser != null){
                CommonScaffold(navController = navController, userViewModel = userViewModel, defaultCar) {
                    SummaryPage( navController, carViewModel, userViewModel, defaultCar)
                }
            }
            else{
                navController.navigate("LoginScreenRoute")
            }
        }
        composable("AboutScreenRoute") {
            if(auth.currentUser != null){
                CommonScaffold(navController = navController, userViewModel = userViewModel, defaultCar) {
                    About()
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
                    CommonScaffold(navController = navController, userViewModel = userViewModel, defaultCar) {
                        UserProfilePage(
                            userViewModel = userViewModel,
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
