package com.example.gamelibrary.app

import java.util.jar.Attributes

data class GameFile (
    val title   : String,
    val year    : String
        ) {
    lateinit var platform : String
    var attributes = hashMapOf<String, Boolean>(
        "physical" to false, "digital" to false, "case" to false, "manual" to false)
}
