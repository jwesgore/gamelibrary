package com.example.gamelibrary.app

import com.google.firebase.firestore.DocumentSnapshot
import java.io.Serializable

data class GameFile (
    val title   : String,
    val year    : String
        ) : Serializable {

    constructor(snapshot: DocumentSnapshot) :
            this(snapshot["title"].toString(), snapshot["year"].toString()) {
                platform = snapshot["platform"].toString()
                platformAbv = snapshot["platformAbv"].toString()
                attributes = snapshot["attributes"] as Map<String, Boolean>
            }


    lateinit var platform    : String
    lateinit var platformAbv : String
    var attributes = mapOf<String, Boolean>(
        "physical" to false,
        "digital" to false,
        "case" to false,
        "manual" to false)



}
