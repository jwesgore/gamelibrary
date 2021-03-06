package com.example.gamelibrary

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.gamelibrary.app.*
import com.example.gamelibrary.login.loginComm
import com.example.gamelibrary.login.signup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity(), loginComm, appComm {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var data: ArrayList<GameFile>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Auth
        mAuth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()
        val user = mAuth.currentUser
        if (user != null) {
            commLogin(user)
        }
    }

    // loginComm methods
    // login user
    override fun commLogin(user: FirebaseUser) {
        supportFragmentManager.beginTransaction().
            replace(R.id.mainFragmentContainerView, Home()).addToBackStack(null).
            commit()
    }

    // check login credentials
    override fun commLogin(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")

                    val user = mAuth.currentUser
                    if (user != null) { commLogin(user) }

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

    // appComm Methods
    // update recycler data
    override fun UpdateRecycler(data : ArrayList<GameFile>) {
        this.data = data

        val recyclerView : RecyclerView = findViewById(R.id.Home_recyclerView)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun gotoAddGame() {
        supportFragmentManager.beginTransaction().
            replace(R.id.mainFragmentContainerView, AddGame()).
            addToBackStack(null).commit()
    }

    override fun addGameFile(game: GameFile) {
        supportFragmentManager.popBackStack()
        val db   = Firebase.firestore
        db.collection("switch").add(game)
    }

    override fun onItemClick(position: Int) {
        val game = data[position]
        val bundle = Bundle()
        val frag = GameDetails()

        bundle.putSerializable("game",game)
        frag.arguments = bundle

        supportFragmentManager.beginTransaction().
        replace(R.id.mainFragmentContainerView, frag).
        addToBackStack(null).commit()
    }

}