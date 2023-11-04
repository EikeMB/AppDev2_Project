package com.example.appdev2_assignment2

import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.compose.AppDev2_Assignment2Theme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppDev2_Assignment2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()

                }
            }
        }
    }

}

/*
Composable made up of the full page
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    var showTitleScreen by rememberSaveable { mutableStateOf(true) }
    val navController = rememberNavController()

    if (showTitleScreen) {
        SignUpScreen(onContinueClicked = { showTitleScreen = false })
    } else {

    Scaffold(
        topBar = {
            TopAppBar(
                { Title() }
            )
        },
        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                color = MaterialTheme.colorScheme.background
            ) {

                Router(navController)

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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
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

        // Title
        Text(
            text = "App Title", // Your title text
            modifier = Modifier.padding(top = 50.dp, bottom = 125.dp)
        )

        //Input box for Username
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

        //Input box for Password
        TextField(
            value = password,
            onValueChange = { newText ->
                password = newText
            },
            placeholder = { Text("Enter your Password") }, // Add the placeholder
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        //Button for Login
        Button(
            modifier = Modifier.padding(vertical = 10.dp),
            onClick = onContinueClicked,
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary)
        ) {
            Text("Login")
        }

        //Button for Signup
        Button(
            onClick = onContinueClicked,
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary)
        ) {
            Text("Sign up")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var Age by remember { mutableStateOf("18") }
    var userImage by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(1.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Title
        Text(
            text = "App Title", // Your title text
            modifier = Modifier.padding(top = 50.dp, bottom = 50.dp)
        )

        //Input box for Username
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

        //Input box for Password
        TextField(
            value = password,
            onValueChange = { newText ->
                password = newText
            },
            placeholder = { Text("Enter your Password") }, // Add the placeholder
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        //Input box for Confirm Password
        TextField(
            value = password,
            onValueChange = { newText ->
                password = newText
            },
            placeholder = { Text("Enter your Password") }, // Add the placeholder
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        //Input box for Confirm Password
        TextField(
            value = confirmPassword,
            onValueChange = { newText ->
                confirmPassword = newText
            },
            placeholder = { Text("Enter your Confirm Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        //Input box for Age
        TextField(
            value = Age,
            onValueChange = { newText ->
                Age = newText
            },
            placeholder = { Text("Enter your Age") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        ImageChangingButton();

        //Button for Signup
        Button(
            onClick = onContinueClicked,
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary)
        ) {
            Text("Sign up")
        }

        //Button for Login
        Button(
            modifier = Modifier.padding(vertical = 10.dp),
            onClick = onContinueClicked,
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary)
        ) {
            Text("Login")
        }
    }
}

@Composable
fun ImageChangingButton() {
    //current image change on click
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
            modifier = Modifier.size(100.dp)
        )
    }
}

@Composable
fun Title(){
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
fun Page1() {

    Column (
        modifier = Modifier
            .fillMaxSize()


    ){

        Text(text = "First Page")
    }

}

@Composable
fun Router(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "MainScreenRoute") {
        composable("MainScreenRoute") { Page1() }
        composable("AboutScreenRoute") { Title() }

    }
}




