package com.example.gamelibrary.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.gamelibrary.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [login.newInstance] factory method to
 * create an instance of this fragment.
 */
class login : Fragment() {

    private val TAG = "Login Fragment"

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_login, container, false)
        val loginInterface : loginComm = activity as loginComm

        // view references
        val email    : EditText = view.findViewById(R.id.Login_editTextEmail)
        val password : EditText = view.findViewById(R.id.Login_editTextPassword)

        val signin   : Button   = view.findViewById(R.id.Login_button)
        val signup   : TextView = view.findViewById(R.id.Login_textViewSignup)
        val forgot   : TextView = view.findViewById(R.id.Login_textViewForgot)

        // onclick methods
        signin.setOnClickListener {
            // get input
            val emailIn = email.text.toString()
            val passIn  = password.text.toString()

            // submit to Firestore


            loginInterface.commLogin()
        }
        signup.setOnClickListener {
            loginInterface.commSignup()
        }
        forgot.setOnClickListener {
            loginInterface.commForgot()
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment login.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            login().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}