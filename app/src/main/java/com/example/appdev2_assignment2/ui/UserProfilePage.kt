package com.example.appdev2_assignment2.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.appdev2_assignment2.AppUser
import com.example.appdev2_assignment2.R
import com.example.appdev2_assignment2.User
import com.example.appdev2_assignment2.ViewModels.CarViewModel
import com.example.appdev2_assignment2.ViewModels.UserViewModel
import com.example.appdev2_assignment2.auth.delete
import com.example.appdev2_assignment2.auth.signOut
import com.google.firebase.auth.FirebaseAuth


/**
 * Composable for the user profile page.
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun UserProfilePage(
    userViewModel: UserViewModel,
    navController: NavController,
    auth: FirebaseAuth,
    carViewModel: CarViewModel
)  {
    val user by userViewModel.activeUser.collectAsState(initial = AppUser("", "", 0, ""))
    var newImageLink by remember { mutableStateOf("") }
    var newNameText by remember{ mutableStateOf("") }
    var newAgeText by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Profile Picture
        GlideImage(
            model = user.picture,
            contentDescription = "profile picture",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(color = MaterialTheme.colorScheme.primary)
                .border(2.dp, color = MaterialTheme.colorScheme.onBackground, shape = CircleShape)
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )

        //New image link input
        TextField(
            value = newImageLink,
            onValueChange = { newImageLink = it },
            label = { Text(text = "New Image Link") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Change Profile Picture Button
        Button(
            onClick = {
                var newUser = user
                newUser.picture = newImageLink
                userViewModel.addUser(newUser)
                newImageLink = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Change Profile Picture") }

        // User's Name
        Text("Name: ${user.name}", modifier = Modifier.semantics {
            this.contentDescription = "User Name" })
        TextField(
            value = newNameText,
            onValueChange = { newNameText = it },
            label = { Text("New Name") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        // Change Name Button
        Button(
            onClick = {
                var newUser = user
                newUser.name = newNameText
                userViewModel.addUser(newUser)
                newNameText = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Change Name")
        }

        // User's Age
        Text("Age: ${user.age}", modifier = Modifier.semantics {
            this.contentDescription = "User Age" }
        )
        TextField(
            value = newAgeText,
            onValueChange = { newAgeText = it },
            label = { Text("New Age") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Change Age Button
        Button(
            onClick = {
                var newUser = user
                newUser.age = newAgeText.toInt()
                userViewModel.addUser(newUser)
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Change Age") }

        // Sign Out Button
        Button(
            onClick = {
                signOut(auth, navController)
            },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ){ Text(text = "Sign Out", color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold) }
        val cars by carViewModel.userCars.collectAsState(initial = emptyList())

        // Delete Account Button
        Button(
            onClick = { delete(auth, navController, userViewModel, user, carViewModel, cars) },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ){ Text(text = "Delete account", color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold) }
    }
}