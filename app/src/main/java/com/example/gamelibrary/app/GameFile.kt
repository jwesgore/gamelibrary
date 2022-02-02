package com.example.gamelibrary.app

import java.io.Serializable

data class GameFile (
    val title   : String,
    val year    : String
        ) : Serializable {
    lateinit var platform    : String
    lateinit var platformAbv : String
    var attributes = hashMapOf<String, Boolean>(
        "physical" to false,
        "digital" to false,
        "case" to false,
        "manual" to false)

}
