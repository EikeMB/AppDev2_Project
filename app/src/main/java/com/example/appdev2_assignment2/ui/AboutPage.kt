package com.example.appdev2_assignment2.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth


@Composable
fun About(auth: FirebaseAuth, navController: NavController){
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