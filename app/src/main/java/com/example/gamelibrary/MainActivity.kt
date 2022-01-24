package com.example.gamelibrary

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gamelibrary.login.loginComm
import com.example.gamelibrary.login.signup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity(), loginComm {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Auth
        mAuth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
//        if (currentUser != null) {
//            reload()
//        }
    }

    // loginComm methods
    override fun commLogin(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    val user = mAuth.currentUser

                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    // send user to sign up
    override fun commSignup() {
        supportFragmentManager.beginTransaction().replace(R.id.mainFragmentContainerView, signup()).addToBackStack(null).commit()
    }

    // sign up a user
    override fun commSignup(name: String, email: String, password: String) {
       mAuth.createUserWithEmailAndPassword(email, password)
           .addOnCompleteListener(this) { task ->
               if (task.isSuccessful) {
                   Log.d(TAG, "createUserWithEmail:success")
                   val user = mAuth.currentUser

               } else {
                   Log.w(TAG, "createUserWithEmail:failure", task.exception)
                   Toast.makeText(baseContext, "Authentication failed.",
                       Toast.LENGTH_SHORT).show()
               }
           }
    }

    override fun commForgot() {
        TODO("Not yet implemented")
    }
}