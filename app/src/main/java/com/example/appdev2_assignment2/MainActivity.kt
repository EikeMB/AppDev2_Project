package com.example.appdev2_assignment2

import android.os.Bundle
import android.widget.Button
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppDev2_Assignment2Theme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import androidx.navigation.compose.NavHost
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
fun MyApp(){

    var A by rememberSaveable { mutableStateOf(1) }

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
                if(A == 1){
                    Page1()
                }
                else{
                    Title()
                }

            }
        },
        bottomBar = {
            BottomAppBar {
                IconButton(onClick = { A = 2 } ,modifier = Modifier.weight(1f)) {
                    Icon(Icons.Filled.Home, contentDescription = "Home")
                }
                IconButton(onClick = {  A = 1  },modifier = Modifier.weight(1f)) {
                    Icon(Icons.Filled.Build, contentDescription = "Create")
                }
                IconButton(onClick = { /*TODO*/ },modifier = Modifier.weight(1f)) {
                    Icon(Icons.Filled.Person, contentDescription = "User")
                }
                IconButton(onClick = { /*TODO*/ },modifier = Modifier.weight(1f)) {
                    Icon(Icons.Filled.Face, contentDescription = "About Us")
                }
            }
        }
    )
}

/*
Composable to display all cars in a list
 */
@Composable
fun Cars(chosenCompany:String){
    LazyColumn(){
        items( cars.filter { car -> car.company.name == chosenCompany }){ car ->
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(10.dp)

            ) {
                Card{
                    Row(modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween){
                        Image(painter = painterResource(id = car.image),
                            contentDescription ="",
                            modifier = Modifier
                                .clip(
                                    CircleShape
                                )
                                .size(100.dp),
                            contentScale = ContentScale.Crop)
                        Text(car.company.name + " " + car.name,
                            modifier = Modifier.align(alignment = Alignment.CenterVertically),
                            color = MaterialTheme.colorScheme.onBackground)
                    }
                }

            }
        }
    }
}

/*
Displays the app title
 */
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
        var clicked by rememberSaveable{mutableStateOf(false)}

        var chosenCompany by rememberSaveable{ mutableStateOf("")}

        Row(Modifier.padding(10.dp), horizontalArrangement = Arrangement.SpaceEvenly){

            for(company in carCompanies){
                Button(onClick = { clicked = true
                    chosenCompany = company.name}, modifier = Modifier.size(120.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background)
                ) {
                    Column(modifier = Modifier.align(alignment = Alignment.CenterVertically)) {
                        Image(painter = painterResource(id = company.logo),
                            contentDescription ="" )
                        Text(company.name,
                            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.bodyMedium)
                    }

                }
            }
        }

        if(clicked){
            Cars(chosenCompany)
        }
    }

}




