package com.example.appdev2_assignment2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appdev2_assignment2.AppUser
import com.example.appdev2_assignment2.StartupPage
import com.example.appdev2_assignment2.User
import com.example.appdev2_assignment2.ViewModels.CarPartViewModel
import com.example.appdev2_assignment2.ViewModels.CarViewModel
import com.example.appdev2_assignment2.ViewModels.UserViewModel
import com.example.appdev2_assignment2.ui.About
import com.example.appdev2_assignment2.ui.CommonScaffold
import com.example.appdev2_assignment2.ui.HomePage
import com.example.appdev2_assignment2.ui.LoginScreen
import com.example.appdev2_assignment2.ui.SignUpScreen
import com.example.appdev2_assignment2.ui.UserProfilePage
import com.google.firebase.auth.FirebaseAuth


@Composable
fun MainNavHost(navController: NavHostController, auth: FirebaseAuth, userViewModel: UserViewModel, carViewModel: CarViewModel, partViewModel: CarPartViewModel){
    NavHost(navController = navController, startDestination = "LoginScreenRoute") {
        composable("LoginScreenRoute") {
            LoginScreen(navController, auth = auth, userViewModel = userViewModel)
        }
        composable("SignUpScreenRoute") {
            SignUpScreen(navController, auth = auth, userViewModel = userViewModel)
        }
        composable("MainScreenRoute") {
            CommonScaffold(navController = navController, userViewModel = userViewModel) {
            HomePage(auth, navController, carViewModel, partViewModel, userViewModel)
            }
        }
        composable("AboutScreenRoute") {
            CommonScaffold(navController = navController, userViewModel = userViewModel) {
                About(auth, navController)
            }
        }
        composable("UserProfileRoute") {
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
    }
}
