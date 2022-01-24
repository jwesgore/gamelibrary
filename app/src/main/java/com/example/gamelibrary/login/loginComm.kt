package com.example.gamelibrary.login

import com.google.firebase.auth.FirebaseUser

interface loginComm {
    fun commLogin(user: FirebaseUser): Unit
    fun commLogin(email: String, password: String) : Unit
    fun commSignup(): Unit
    fun commSignup(name: String, email: String, password: String): Unit
    fun commForgot(): Unit
}