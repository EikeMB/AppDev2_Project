package com.example.appdev2_assignment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.compose.AppDev2_Assignment2Theme
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appdev2_assignment2.CRUD.CarPartRepositoryFirestore
import com.example.appdev2_assignment2.CRUD.CarRepositoryFirestore
import com.example.appdev2_assignment2.CRUD.UserRepositoryFirestore
import com.example.appdev2_assignment2.ViewModels.CarPartViewModel
import com.example.appdev2_assignment2.ViewModels.CarViewModel
import com.example.appdev2_assignment2.ViewModels.UserViewModel
import com.example.appdev2_assignment2.navigation.MainNavHost
import com.example.appdev2_assignment2.ui.TopBar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lateinit var auth: FirebaseAuth

        auth = Firebase.auth

        val db = FirebaseFirestore.getInstance()


        var carRepository = CarRepositoryFirestore(db)
        var carViewModel = CarViewModel(carRepository)

        var carPartRepository = CarPartRepositoryFirestore(db)
        var carPartViewModel = CarPartViewModel(carPartRepository)

        var userRepository = UserRepositoryFirestore(db)
        var userViewModel = UserViewModel(userRepository)



        setContent {
            AppDev2_Assignment2Theme {
                // A surface container using the 'background' color from the theme
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartupPage(auth: FirebaseAuth, carViewModel: CarViewModel, partViewModel: CarPartViewModel, userViewModel: UserViewModel) {


    val navController = rememberNavController()

    MainNavHost(
        navController = navController,
        auth = auth,
        userViewModel = userViewModel,
        carViewModel = carViewModel,
        partViewModel = partViewModel
    )
}





