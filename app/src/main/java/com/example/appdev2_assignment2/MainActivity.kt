package com.example.appdev2_assignment2

import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.compose.AppDev2_Assignment2Theme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appdev2_assignment2.CRUD.addCar
import com.example.appdev2_assignment2.CRUD.getCarsFromUser
import com.example.appdev2_assignment2.auth.signIn
import com.example.appdev2_assignment2.auth.signOut
import com.example.appdev2_assignment2.auth.signUp
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lateinit var auth: FirebaseAuth

        auth = Firebase.auth
        setContent {
            AppDev2_Assignment2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StartupPage(auth)

                }
            }
        }
    }

}
fun parseCar(docSnapshot: DocumentSnapshot): Car {
    val name = docSnapshot.getString("name") ?: ""
    val vin = docSnapshot.getLong("vin")?.toInt() ?: 0
    val parts = (docSnapshot.get("parts") as? List<Map<*, *>>)?.map { parseCarPart(it) } ?: emptyList()
    return Car(name, parts, vin)
}

fun parseCarPart(partData: Map<*, *>): CarPart {
    val name = partData["name"] as? String ?: ""
    val image = (partData["image"] as? Number)?.toInt() ?: 0
    val modelNum = (partData["modelNum"] as? Number)?.toInt() ?: 0
    val description = partData["description"] as? String ?: ""
    val carPartTypeStr = partData["type"] as? String ?: ""
    val carPartType = CarPartType.valueOf(carPartTypeStr)

    return CarPart(name, image, modelNum, description, carPartType)
}
@Composable
fun FirestoreDataList() {
    var cars by remember { mutableStateOf(emptyList<Car>()) }
    var searchText by remember { mutableStateOf("") }

    val firestore = FirebaseFirestore.getInstance()

    // Fetch data from Firestore when the Composable is first displayed
    LaunchedEffect(Unit) {
        val querySnapshot = firestore.collection("cars").get().await()
        cars = querySnapshot.documents.map { documentSnapshot ->  parseCar(documentSnapshot)}
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        LazyRow(
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(cars) { car ->
                Card(
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = car.name
                            )
                            Text(
                                text = "VIN: ${car.vin}"
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            car.parts.forEach { carPart ->
                                Text(
                                    text = carPart.name
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartupPage(auth: FirebaseAuth) {


    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "LoginScreenRoute") {
        composable("LoginScreenRoute") {
            LoginScreen(navController, auth = auth)
        }
        composable("SignUpScreenRoute") {
            SignUpScreen(navController, auth = auth)
        }
        composable("MainScreenRoute") {
            HomeScreen(navController, auth = auth)
        }
    }
}

@Composable
fun TopBar(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.End
    ){
        Image(
            painter = painterResource(id = R.drawable.profileicon),
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
    }
}
/*
Composable made up of the full page
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, auth: FirebaseAuth) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                { TopBar() }
            )
        },
        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                color = MaterialTheme.colorScheme.background
            ) {

                Router(navController, auth)

            }
        },
        bottomBar = {
            BottomAppBar {
                IconButton(onClick = { navController.navigate("MainScreenRoute") },
                    modifier = Modifier.weight(1f)) {
                    Icon(Icons.Filled.Home, contentDescription = "Home")
                }
                IconButton(
                    onClick = { navController.navigate("AboutScreenRoute") },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Filled.Build, contentDescription = "Create")
                }
                IconButton(onClick = { }, modifier = Modifier.weight(1f)) {
                    Icon(Icons.Filled.Person, contentDescription = "User")
                }
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.weight(1f)) {
                    Icon(Icons.Filled.Face, contentDescription = "About Us")
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    auth: FirebaseAuth
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }



    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // TITLE
        Text(
            text = "App Title", // Your title text
            modifier = Modifier.padding(top = 50.dp, bottom = 125.dp)
        )

        //USERNAME INPUT
        TextField(
            value = username,
            onValueChange = { newText ->
                username = newText
            },
            placeholder = { Text("Enter your Username") }, // Add the placeholder
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        //PASSWORD INPUT
        TextField(
            value = password,
            onValueChange = { newText ->
                password = newText
            },
            visualTransformation = PasswordVisualTransformation(),
            placeholder = { Text("Enter your Password") }, // Add the placeholder
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        //LOGIN BUTTON
        Button(
            modifier = Modifier.padding(vertical = 10.dp),
            onClick = {

                signIn(auth, username, password, navController)


            },
        ) {
            Text("Login")
        }

        //SIGN UP BUTTON
        Button(
            onClick = { navController.navigate("SignUpScreenRoute") },
        ) {
            Text("Sign up")
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    auth: FirebaseAuth
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("18") }

    // ERROR INPUT FIELD
    var usernameError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }
    var ageError by remember { mutableStateOf<String?>(null) }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // TITLE
        Text(
            text = "App Title", // Your title text
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // USERNAME INPUT
        TextField(
            value = username,
            onValueChange = { newText ->
                username = newText
            },
            label = { Text("Enter your Username") }, // Add the placeholder
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            isError = usernameError != null,
        )
        usernameError?.let { error ->
            Text(text = error, color = Color.Red, modifier = Modifier.padding(vertical = 5.dp))
        }

        //PASSWORD INPUT
        TextField(
            value = password,
            onValueChange = { newText ->
                password = newText
            },
            visualTransformation = PasswordVisualTransformation(),
            label = { Text("Enter your Password") }, // Add the placeholder
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            isError = passwordError != null,
        )
        passwordError?.let { error ->
            Text(text = error, color = Color.Red, modifier = Modifier.padding(vertical = 5.dp))
        }

        //CONFIRM PASSWORD
        TextField(
            value = confirmPassword,
            onValueChange = { newText ->
                confirmPassword = newText
            },
            visualTransformation = PasswordVisualTransformation(),
            label  = { Text("Enter your Confirm Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            isError = confirmPasswordError != null,
        )
        confirmPasswordError?.let { error ->
            Text(text = error, color = Color.Red, modifier = Modifier.padding(8.dp))
        }

        //AGE BOX
        TextField(
            value = age,
            onValueChange = { newText ->
                age = newText
            },
            label = { Text("Enter your Age") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            isError = ageError != null,
        )
        ageError?.let { error ->
            Text(text = error, color = Color.Red, modifier = Modifier.padding(vertical = 5.dp))
        }

        //USER'S IMAGE
        ImageChangingButton();

        //REGISTER BUTTON
        Button(
            modifier = Modifier.padding(vertical = 10.dp),
            onClick = {
                if (validateInputSignUp(username, password, confirmPassword, age)) {
                    navController.navigate("LoginScreenRoute")
                } else {
                    usernameError = if (username.isEmpty()) "Username required" else null
                    passwordError = if (password.isEmpty()) "Password  required" else null
                    confirmPasswordError = if (password != confirmPassword) "Confirm Password does not match Password" else null
                    ageError = if (age.isEmpty() || age.toIntOrNull() == null) "Invalid age" else null
                }
                signUp(auth, username, password, navController)
            },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary)
        ) {
            Text("Register")
        }

        //LOGIN BUTTON
        Button(
            onClick = {navController.navigate("LoginScreenRoute")},
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary)
        ) {
            Text("Login")
        }
    }
}

@Composable
fun ImageChangingButton() {
    var isImage1Visible by remember { mutableStateOf(true) }
    val currentImage = if (isImage1Visible) R.drawable.ferrari else R.drawable.mercedes

    Button(
        onClick = {
            isImage1Visible = !isImage1Visible
        }
    ) {
        Image(
            painter = painterResource(id = currentImage),
            contentDescription = "Image",
            modifier = Modifier.size(50.dp)
        )
    }
}

fun validateInputSignUp(
    username: String,
    password: String,
    confirmPassword: String,
    age: String
): Boolean {
    val isUsernameValid = username.isNotEmpty()
    val isPasswordValid = password.isNotEmpty()
    val doesPasswordsMatch = password == confirmPassword
    val isAgeValid = age.toIntOrNull() != null

    return isUsernameValid && isPasswordValid && doesPasswordsMatch && isAgeValid
}

@Composable
fun Title(auth: FirebaseAuth, navController: NavController){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp)
    ) {
        Text(
            text = "Title",
            modifier = Modifier.weight(3f)
        )
        Text(
            text = "Name",
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun Page1(auth: FirebaseAuth, navController: NavController) {

    Column (
        modifier = Modifier
            .fillMaxSize()


    ){

        var cars by remember { mutableStateOf<List<Car>>(emptyList())}
        var loading by remember { mutableStateOf(true)}


        LaunchedEffect(Unit){
            val user = auth.currentUser?.email?.let { User(it) }
            val userCars = user?.let { getCarsFromUser(it) }

            withContext(Dispatchers.Main){
                cars = userCars!!
                loading = false
            }

        }
        
        Box{
            if(loading){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            else{
                LazyRowCars(cars = cars)
            }
        }
    }

}



@Composable
fun LazyRowCars(cars: List<Car>){
    LazyRow {
        items(cars) { car ->
            CarCard(car = car)
        }
    }
}

@Composable
fun CarCard(car: Car){
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(200.dp, 250.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = car.name, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "VIN: ${car.vin}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun Router(navController: NavHostController, auth: FirebaseAuth) {
    NavHost(navController = navController, startDestination = "MainScreenRoute") {
        composable("MainScreenRoute") { Page1(auth, navController) }
        composable("AboutScreenRoute") { Title(auth, navController) }

    }
}




