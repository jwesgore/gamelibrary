package com.example.gamelibrary.login

interface loginComm {
    fun commLogin() : Unit
    fun commSignup(): Unit
    fun commSignup(name: String, email: String, password: String): Unit
    fun commForgot(): Unit
}