package com.example.car_builder.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.car_builder.AppUser
import com.example.car_builder.Car
import com.example.car_builder.ViewModels.UserViewModel

/**
 * Composable for top app bar displaying the title and user name and age.
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TopBar(userViewModel: UserViewModel, navController: NavHostController) {

    val user by userViewModel.activeUser.collectAsState(initial = AppUser("", "", 0, ""))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Car Builder",
            modifier = Modifier.weight(3f),
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onPrimary
        )
        GlideImage(
            model = user.picture,
            contentDescription = "Profile picture",
            modifier = Modifier
                .size(48.dp)
                .weight(2f)
                .clip(CircleShape)
                .clickable { navController.navigate("UserProfileRoute") },
        )
        Column {
            Text(
                text = "Name: ${user.name}",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onPrimary

            )
            Text(
                text = "Age: ${user.age}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

/*
Composable made up of the full page
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonScaffold(navController: NavHostController, userViewModel: UserViewModel, defaultCar:
MutableState<Car?>,  content: @Composable (PaddingValues) -> Unit
) {


    Scaffold(
        topBar = {
            TopAppBar(
                title = { TopBar(userViewModel, navController) },
                colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.primary),

                )
        },
        bottomBar = {
            BottomAppBar {
                IconButton(onClick = { navController.navigate("MainScreenRoute") },
                    modifier = Modifier.weight(1f)) {
                    Icon(Icons.Filled.Home, contentDescription = "Home")
                }
                IconButton(
                    onClick = {
                                defaultCar.value = null
                                navController.navigate("CreateScreenRoute")
                              },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Filled.Build, contentDescription = "Create")
                }
                IconButton(onClick = { navController.navigate("UserProfileRoute")}, modifier = Modifier.weight(1f)) {
                    Icon(Icons.Filled.Person, contentDescription = "User")
                }
                IconButton(onClick = { navController.navigate("AboutScreenRoute") }, modifier = Modifier.weight(1f)) {
                    Icon(Icons.Filled.Face, contentDescription = "About Us")
                }
            }
        }
    ){innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            content(innerPadding)
        }
    }
}
