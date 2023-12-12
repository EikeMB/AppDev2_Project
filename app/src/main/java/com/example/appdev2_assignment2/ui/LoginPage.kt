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
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appdev2_assignment2.ViewModels.UserViewModel
import com.example.appdev2_assignment2.auth.signIn
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.material3.LocalTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.sp
import com.example.appdev2_assignment2.R
import com.example.compose.darkBluePrimary
import com.example.compose.greySurface
import com.example.compose.lightBlueOnPrimary
import com.example.compose.lightBluePrimary
import com.example.compose.lightBluePrimaryContainer

/**
 * Allows users to input their username and password to login in.
 */
@SuppressLint("UnrememberedMutableState")
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

    var error = remember { mutableStateOf<String?>(null) }
    var usernameError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember {
        mutableStateOf<String?>(null)
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TITLE
        Text(
            text = "Car Builder App",
            color = darkBluePrimary,
            modifier = Modifier.padding(top = 50.dp, bottom = 125.dp),
            style = LocalTextStyle.current.copy(
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
        )

        //USERNAME INPUT
        TextField(
            value = username,
            onValueChange = { newText -> username = newText },
            placeholder = { Text("Enter your Username...", color = lightBlueOnPrimary) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        usernameError?.let { error ->
            Text(
                text = error,
                color = Color.Red.copy(alpha = 0.6f),
                modifier = Modifier.padding(vertical = 5.dp)
            )
        }


        //PASSWORD INPUT
        TextField(
            value = password,
            onValueChange = { newText -> password = newText },
            visualTransformation = PasswordVisualTransformation(),
            placeholder = { Text("Enter your Password...", color = lightBlueOnPrimary) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        passwordError?.let { error ->
            Text(text = error, color = Color.Red, modifier = Modifier.padding(vertical = 5.dp)) }

        error.value?.let { error -> Text(text = error, color = Color.Red, modifier = Modifier.padding(vertical = 5.dp)) }

        //LOGIN BUTTON
        Button(
            modifier = Modifier.padding(vertical = 10.dp),
            onClick = {
                error.value = null
                usernameError = null
                passwordError = null

                if(username.isNotEmpty() && password.isNotEmpty()){
                    signIn(auth, username, password, navController, userViewModel, error)
                }
                else{
                    usernameError = if (username.isEmpty()) "Username required" else null
                    passwordError = if (password.isEmpty()) "Password  required" else null
                }
            },
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                rememberUpdatedState(Color(0xFFADD8E6)).value,
            ),
        ) { Text("Login", color = darkBluePrimary, fontWeight = FontWeight.Bold) }

        //SIGN UP BUTTON
        Button(
            onClick = { navController.navigate("SignUpScreenRoute") },
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(rememberUpdatedState(Color(0xFFADD8E6)).value),
        ) { Text("Sign up", color = darkBluePrimary, fontWeight = FontWeight.Bold) }
    }
}