package com.example.car_builder.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.car_builder.R

/**
 * Displays information about the app and contact details of the creators.
 */
@Composable
fun About(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.a),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "About Us",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Welcome to our app! This Car Builder App is made by Eike, Yensan, and Kevin. Feel free to explore and enjoy the features we've designed for you.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Contact Us",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Contact Information
        val uriHandler = LocalUriHandler.current

        // Clickable email
        Text(
            text = "Email: eikedoodle1@gmail.com",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .clickable { uriHandler.openUri("mailto:eikedoodle1@gmail.com") }
        )

        // Clickable phone number
        Text(
            text = "Phone: +123 456 7890",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.clickable { uriHandler.openUri("tel:+1234567890") }
        )
    }
}