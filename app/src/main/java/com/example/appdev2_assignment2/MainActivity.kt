package com.example.appdev2_assignment2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.RadioButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.asFlow
import androidx.navigation.NavController
import com.example.appdev2_assignment2.CRUD.CarPartRepositoryFirestore
import com.example.appdev2_assignment2.CRUD.CarRepositoryFirestore
import com.example.appdev2_assignment2.CRUD.UserRepositoryFirestore
import com.example.appdev2_assignment2.auth.signIn
import com.example.appdev2_assignment2.auth.signUp
import com.example.appdev2_assignment2.ui.UserProfilePage
import com.example.appdev2_assignment2.CarPart
import com.example.appdev2_assignment2.ViewModels.CarPartViewModel
import com.example.appdev2_assignment2.ViewModels.CarViewModel
import com.example.appdev2_assignment2.ViewModels.UserViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


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


        for(car in cars){
            carViewModel.addCar(car)
        }
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

    NavHost(navController = navController, startDestination = "LoginScreenRoute") {
        composable("LoginScreenRoute") {
            LoginScreen(navController, auth = auth, userViewModel = userViewModel)
        }
        composable("SignUpScreenRoute") {
            SignUpScreen(navController, auth = auth, userViewModel = userViewModel)
        }
        composable("MainScreenRoute") {
            HomeScreen(navController, auth = auth, carViewModel, partViewModel, userViewModel)
        }
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Title",
            modifier = Modifier.weight(3f),
            fontSize = 24.sp
        )
        Image(
            painter = painterResource(id = R.drawable.profileicon),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .weight(1f)
        )
        Column {
            Text(
                text = "Name",
                fontSize = 16.sp
            )
            Text(
                text = "Age",
                fontSize = 14.sp
            )
        }
    }
}

/*
Composable made up of the full page
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, auth: FirebaseAuth, carViewModel: CarViewModel, partViewModel: CarPartViewModel, userViewModel: UserViewModel) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { TopBar() },
                colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.primary),

            )
        },
        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                color = MaterialTheme.colorScheme.background
            ) {

                Router(navController, auth, carViewModel, partViewModel, userViewModel)

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
                IconButton(onClick = { navController.navigate("UserProfileRoute")}, modifier = Modifier.weight(1f)) {
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
    auth: FirebaseAuth,
    userViewModel: UserViewModel
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
    auth: FirebaseAuth,
    userViewModel: UserViewModel
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
                userViewModel.addUser(AppUser(username, username, age.toInt(), 0))
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

@SuppressLint("StateFlowValueCalledInComposition", "CoroutineCreationDuringComposition")
@Composable
fun Page1(auth: FirebaseAuth, navController: NavController, carViewModel: CarViewModel, partViewModel: CarPartViewModel, userViewModel: UserViewModel) {

    val user = auth.currentUser?.email?.let { User(it) }

    val coroutineScope = rememberCoroutineScope()



    coroutineScope.launch {
            coroutineScope {
                launch { carViewModel.getCarsForUser(user!!) }
                launch { carViewModel.getAllCars() }
            }
    }



    val cars by carViewModel.userCars.collectAsState(initial = emptyList())
    val allCars by carViewModel.allCars.collectAsState(initial = emptyList())

    Column (
        modifier = Modifier
            .fillMaxSize()
    ){

        Text(text = "Your customized cars: ", Modifier.padding(10.dp))
        Box{
            UserCarsList(cars = cars)
        }
        Text(text = "See other customized car: ", Modifier.padding(10.dp))
        UserCarsListVertical(cars = allCars)
    }
}

@Composable
private fun partSection(partsList: List<CarPart>, partChosenName: String, partChosen: PartType, selectedOption: CarPart, onOptionSelected: (CarPart) -> Unit){
    val expanded = remember { mutableStateOf(false) }

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Column(
                modifier = Modifier.weight(1f).padding(12.dp),
                //verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = partChosenName)
                }
                if (expanded.value) {

                    partsList.filter { it.type == partChosen }.forEach { part ->
                        PartInfo(
                            part = part,
                            selectedOption = selectedOption,
                            onOptionSelected = onOptionSelected
                        )
                    }

                }
            }
            IconButton(
                onClick = { expanded.value = !expanded.value },
            ) {
                Icon(
                    imageVector = if (expanded.value) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expanded.value) {
                        stringResource(R.string.show_less)
                    } else {
                        stringResource(R.string.show_more)
                    }
                )
            }
        }
    }
}


@SuppressLint("StateFlowValueCalledInComposition", "CoroutineCreationDuringComposition")
@Composable
fun Page2(auth: FirebaseAuth, navController: NavController, carViewModel: CarViewModel, partViewModel: CarPartViewModel, userViewModel: UserViewModel) {

    val coroutineScope = rememberCoroutineScope()

    coroutineScope.launch {
        coroutineScope {
            launch { partViewModel.getAllParts()}
        }
    }

    val partList by partViewModel.allParts.collectAsState(initial = emptyList())

    var wheel: CarPart
    var body: CarPart
    var aerodynamic: CarPart
    var engine: CarPart
    var interior: CarPart
    var accessories: CarPart


    val indexOfWheelPart = partList.indexOfFirst { it.type == PartType.Wheels }
    val indexOfBodyPart = partList.indexOfFirst { it.type == PartType.Body }
    val indexOfAerodynamicPart = partList.indexOfFirst { it.type == PartType.Aerodynamics }
    val indexOfEnginePart = partList.indexOfFirst { it.type == PartType.Engine }
    val indexOfInteriorPart = partList.indexOfFirst { it.type == PartType.Interior }
    val indexOfAccessoriesPart = partList.indexOfFirst { it.type == PartType.Accessories }

    wheel = if(partList.isNotEmpty()){ partList[indexOfWheelPart] }
    else{ CarPart("", 0, 0, "", PartType.Wheels) }

    body = if(partList.isNotEmpty()){ partList[indexOfBodyPart] }
    else{ CarPart("", 0, 0, "", PartType.Body) }

    aerodynamic = if(partList.isNotEmpty()){ partList[indexOfAerodynamicPart] }
    else{ CarPart("", 0, 0, "", PartType.Aerodynamics) }

    engine = if(partList.isNotEmpty()){ partList[indexOfEnginePart] }
    else{ CarPart("", 0, 0, "", PartType.Engine) }

    interior = if(partList.isNotEmpty()){ partList[indexOfInteriorPart] }
    else{ CarPart("", 0, 0, "", PartType.Interior) }

    accessories = if(partList.isNotEmpty()){ partList[indexOfAccessoriesPart] }
    else{ CarPart("", 0, 0, "", PartType.Accessories) }

    val (wheelSelectedOption, wheelOnOptionSelected) = remember { mutableStateOf(wheel) }
    val (bodySelectedOption, bodyOnOptionSelected) = remember { mutableStateOf(body) }
    val (aerodynamicSelectedOption, aerodynamicOnOptionSelected) = remember { mutableStateOf(aerodynamic) }
    val (engineSelectedOption, engineOnOptionSelected) = remember { mutableStateOf(engine) }
    val (interiorSelectedOption, interiorOnOptionSelected) = remember { mutableStateOf(interior) }
    val (accessoriesSelectedOption, accessoriesOnOptionSelected) = remember { mutableStateOf(accessories) }


    val creating = true

    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ){

        //partSection(partsList = wheelsList, selectedOption = wheelSelectedOption, onOptionSelected = wheelOnOptionSelected)
        partSection(partsList = partList,partChosenName = "Wheels", partChosen = PartType.Wheels, selectedOption = wheelSelectedOption, onOptionSelected = wheelOnOptionSelected)
        partSection(partsList = partList,partChosenName = "Body", partChosen = PartType.Body, selectedOption = bodySelectedOption, onOptionSelected = bodyOnOptionSelected)
        partSection(partsList = partList,partChosenName = "Aerodynamic", partChosen = PartType.Aerodynamics, selectedOption = accessoriesSelectedOption, onOptionSelected = accessoriesOnOptionSelected)
        partSection(partsList = partList,partChosenName = "Engine", partChosen = PartType.Engine, selectedOption = engineSelectedOption, onOptionSelected = engineOnOptionSelected)
        partSection(partsList = partList,partChosenName = "Interior", partChosen = PartType.Interior, selectedOption = interiorSelectedOption, onOptionSelected = interiorOnOptionSelected)
        partSection(partsList = partList,partChosenName = "Accessories", partChosen = PartType.Accessories, selectedOption = accessoriesSelectedOption, onOptionSelected = accessoriesOnOptionSelected)

        IconButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
        }

        //Later change the if statement to if we modify or create
        IconButton(
            onClick = {
                if (creating) {
                    navController.navigate("MainScreenRoute")
                } else {
                    navController.navigate("UserProfileRoute")
                }

                //Later change it to summary page of the car
                //navController.navigate("UserProfileRoute")
            },
            modifier = Modifier.padding(16.dp)
        ) {
            val icon = if (creating) Icons.Filled.ArrowForward else Icons.Filled.Person
            Icon(icon, contentDescription = "Next")
        }
    }

}



@Composable
private fun PartInfo(
    part: CarPart,
    selectedOption: CarPart,
    onOptionSelected: (CarPart) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    val extraPadding = if (expanded.value) 48.dp else 0.dp

    Surface(
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            //verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
            ){
                RadioButton(
                    selected = (part == selectedOption),
                    onClick = { onOptionSelected(part) },
                    modifier = Modifier.padding(end = 16.dp)
                )
                Text(text = part.name)
            }
            if (expanded.value) {  Text( text = part.name ) }
        }
        IconButton(
            onClick = { expanded.value = !expanded.value }
        ) {
            Icon(
                imageVector = if (expanded.value) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded.value) {
                    stringResource(R.string.show_less)
                }
                else { stringResource(R.string.show_more) }
            )
        }
    }
}

@Composable
fun UserCarsListVertical(cars: List<Car>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
            .background(Color.Blue)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(cars) { index, car ->
                if (index % 2 != 0) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        CarCard(car = car)
                        CarCard(car = cars[index - 1])
                    }
                }
                else{
                    val isLastItem = index == cars.lastIndex
                    if (isLastItem) {
                         CarCard(car = car)
                    }
                }
            }
        }
    }
}

@Composable
fun UserCarsList(cars: List<Car>){
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Blue))
    {
        items(cars) { car ->
            CarCard(car = car)
        }
    }
}

@Composable
fun CarCard(car: Car){
    var image: Int = 0

    for(part in car.parts){
        if(part.type == PartType.Body){
            image = part.image
        }
    }




    Card(
        modifier = Modifier
            .padding(15.dp)
            .height(230.dp)
            .width(170.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp, vertical = 5.dp)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth() // Image occupies full width available
                    .height(150.dp) // Adjust the height as per your preference
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = car.name)
            // Text(text = "VIN: ${car.vin}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun Router(navController: NavHostController, auth: FirebaseAuth, carViewModel: CarViewModel, partViewModel: CarPartViewModel, userViewModel: UserViewModel) {
    NavHost(navController = navController, startDestination = "MainScreenRoute") {
        composable("MainScreenRoute") { Page1(auth, navController, carViewModel, partViewModel, userViewModel) }
        composable("AboutScreenRoute") { Page2(auth, navController, carViewModel, partViewModel, userViewModel) }
        composable("UserProfileRoute") {
            val user = auth.currentUser?.email?.let { User(it) }
            LaunchedEffect(Unit){
                userViewModel.getUser(user!!.email)
            }

            val appUser by userViewModel.activeUser.collectAsState(initial = AppUser("", "", 0,0))

            if (appUser != null) {
                UserProfilePage(
                    user = appUser,
                    onProfilePictureChange = { /* implement the logic */ },
                    onNameChange = { /* implement the logic */ },
                    onAgeChange = { /* implement the logic */ },
                    onPasswordChange = { /* implement the logic */ },
                    onApplyChanges = { /* implement the logic */ },
                )
            }
    }
}}


@Preview
@Composable
fun TopBarPreview() {
    AppDev2_Assignment2Theme {
        TopBar()
    }
}
/*
@Preview
@Composable
fun CarCardPreview() {
    val car = Car("mamadou1@bell.net", "nissan skyline r34", parts1,123) // Replace with your actual car details
    AppDev2_Assignment2Theme {
        CarCard(car = car)
    }
}
*/


