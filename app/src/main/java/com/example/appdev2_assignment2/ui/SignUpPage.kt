package com.example.appdev2_assignment2.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appdev2_assignment2.AppUser
import com.example.appdev2_assignment2.ViewModels.UserViewModel
import com.example.appdev2_assignment2.auth.signUp
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

    // ERROR INPUT FIELD
    var usernameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember {
        mutableStateOf<String?>(null)
    }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }
    var ageError by remember { mutableStateOf<String?>(null) }
    val error = remember { mutableStateOf<String?>(null) }


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

        TextField(
            value = email,
            onValueChange = {newText ->
                email = newText
            },
            label = { Text(text = "Enter your email") },
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
                    var user = AppUser(email, username, age.toInt(), 0)
                    signUp(auth, user, password, navController, userViewModel, error)
                } else {
                    emailError = if (email.isEmpty()) "Email required" else null
                    usernameError = if (username.isEmpty()) "Username required" else null
                    passwordError = if (password.isEmpty()) "Password  required" else null
                    confirmPasswordError = if (password != confirmPassword) "Confirm Password does not match Password" else null
                    ageError = if (age.isEmpty() || age.toIntOrNull() == null) "Invalid age" else null
                }

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