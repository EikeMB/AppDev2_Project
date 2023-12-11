package com.example.appdev2_assignment2.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appdev2_assignment2.AppUser
import com.example.appdev2_assignment2.R
import com.example.appdev2_assignment2.ViewModels.UserViewModel
import com.example.appdev2_assignment2.auth.signUp
import com.example.compose.darkBluePrimary
import com.example.compose.lightBlueOnPrimary
import com.example.compose.lightBluePrimary
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    auth: FirebaseAuth,
    userViewModel: UserViewModel
) {
    var username by remember { mutableStateOf("") }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("18") }
    var picture by remember {
        mutableStateOf("")
    }

    // ERROR INPUT FIELD
    var usernameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember {
        mutableStateOf<String?>(null)
    }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }
    var ageError by remember { mutableStateOf<String?>(null) }
    var pictureError by remember {
        mutableStateOf<String?>(null)
    }
    val error = remember { mutableStateOf<String?>(null) }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(50.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        // TITLE
        Text(
            text = "Sign Up", // Your title text
            color = darkBluePrimary,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 50.dp, bottom = 125.dp)
        )

        TextField(
            value = email,
            onValueChange = {newText ->
                email = newText
            },
            label = { Text(text = "Enter your email", color = lightBlueOnPrimary) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            isError = emailError != null,
        )
        emailError?.let { error ->
            Text(text = error, color = Color.Red, modifier = Modifier.padding(vertical = 5.dp))
        }

        // USERNAME INPUT
        TextField(
            value = username,
            onValueChange = { newText ->
                username = newText
            },
            label = { Text("Enter your Username", color = lightBlueOnPrimary) }, // Add the placeholder
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
            label = { Text("Enter your Password", color = lightBlueOnPrimary) }, // Add the placeholder
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
            label  = { Text("Enter your Confirm Password", color = lightBlueOnPrimary) },
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
            label = { Text("Enter your Age", color = lightBlueOnPrimary) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            isError = ageError != null,
        )
        ageError?.let { error ->
            Text(text = error, color = Color.Red, modifier = Modifier.padding(vertical = 5.dp))
        }

        TextField(
            value = picture,
            onValueChange = { newText ->
                picture = newText
            },
            label = { Text("Enter the link to your profile picture online.", color = lightBlueOnPrimary) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            isError = pictureError != null,
        )
        pictureError?.let { error ->
            Text(text = error, color = Color.Red, modifier = Modifier.padding(vertical = 5.dp))
        }

        error.value?.let { error ->
            Text(text = error, color = Color.Red, modifier = Modifier.padding(vertical = 5.dp))
        }






        //REGISTER BUTTON
        Button(
            modifier = Modifier.padding(vertical = 10.dp),
            onClick = {
                emailError = null
                usernameError = null
                passwordError = null
                confirmPasswordError = null
                ageError = null
                error.value = null
                if (validateInputSignUp(email, username, password, confirmPassword, age)) {
                    var user = AppUser(email, username, age.toInt(), picture)
                    signUp(auth, user, password, navController, userViewModel, error)
                } else {
                    emailError = if (email.isEmpty()) "Email required" else null
                    usernameError = if (username.isEmpty()) "Username required" else null
                    passwordError = if (password.isEmpty()) "Password  required" else null
                    confirmPasswordError = if (password != confirmPassword) "Confirm Password does not match Password" else null
                    ageError = if (age.isEmpty() || age.toIntOrNull() == null) "Invalid age" else null
                    pictureError = if (picture.isEmpty()) "Image required" else null
                }

            },
            colors = ButtonDefaults.buttonColors(
                lightBluePrimary, // Use lightBluePrimary as the background color
                contentColor = lightBlueOnPrimary // Use lightBlueOnPrimary as the content color
            )
        ) {
            Text("Register")
        }

        //LOGIN BUTTON
        Button(
            onClick = {navController.navigate("LoginScreenRoute")},
            colors = ButtonDefaults.buttonColors(
                lightBluePrimary, // Use lightBluePrimary as the background color
                contentColor = lightBlueOnPrimary // Use lightBlueOnPrimary as the content color
            )
        ) {
            Text("Login")
        }
    }
}

fun validateInputSignUp(
    email: String,
    username: String,
    password: String,
    confirmPassword: String,
    age: String
): Boolean {
    val isEmailValid = email.isNotEmpty()
    val isUsernameValid = username.isNotEmpty()
    val isPasswordValid = password.isNotEmpty()
    val doesPasswordsMatch = password == confirmPassword
    val isAgeValid = age.toIntOrNull() != null

    return isEmailValid && isUsernameValid && isPasswordValid && doesPasswordsMatch && isAgeValid
}