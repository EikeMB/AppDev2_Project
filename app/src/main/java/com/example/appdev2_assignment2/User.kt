package com.example.appdev2_assignment2

data class User (
    var email:String,
    var name: String = "",
    var age: Int = 0,
    var password: String = ""
)