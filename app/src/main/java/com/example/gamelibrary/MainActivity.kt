package com.example.gamelibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gamelibrary.login.loginComm
import com.example.gamelibrary.login.signup

class MainActivity : AppCompatActivity(), loginComm {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // loginComm methods
    override fun commLogin() {
        TODO("Not yet implemented")
    }

    override fun commSignup() {
        supportFragmentManager.beginTransaction().replace(R.id.mainFragmentContainerView, signup()).addToBackStack(null).commit()
    }

    override fun commForgot() {
        TODO("Not yet implemented")
    }
}