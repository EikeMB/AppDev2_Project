package com.example.car_builder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.car_builder.CRUD.CarPartRepositoryFirestore
import com.example.car_builder.CRUD.CarRepositoryFirestore
import com.example.car_builder.CRUD.UserRepositoryFirestore
import com.example.car_builder.ViewModels.CarPartViewModel
import com.example.car_builder.ViewModels.CarViewModel
import com.example.car_builder.ViewModels.UserViewModel
import com.example.car_builder.navigation.MainNavHost
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase components
        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()

        // Initialize repositories and view models
        var carRepository = CarRepositoryFirestore(db)
        var carViewModel = CarViewModel(carRepository)
        var carPartRepository = CarPartRepositoryFirestore(db)
        var carPartViewModel = CarPartViewModel(carPartRepository)
        var userRepository = UserRepositoryFirestore(db)
        var userViewModel = UserViewModel(userRepository)


        setContent {
            AppDev2_Assignment2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StartupPage(auth, carViewModel, carPartViewModel, userViewModel)
                }
            }
        }
    }
}

@Composable
fun StartupPage(auth: FirebaseAuth, carViewModel: CarViewModel, partViewModel: CarPartViewModel, userViewModel: UserViewModel) {
    // Create a navigation controller for the app
    val navController = rememberNavController()
    var defaultCar = remember { mutableStateOf <Car?>(null) }

    MainNavHost(
        navController = navController,
        auth = auth,
        userViewModel = userViewModel,
        carViewModel = carViewModel,
        partViewModel = partViewModel,
        defaultCar = defaultCar
    )
}



