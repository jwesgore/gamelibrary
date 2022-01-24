package com.example.gamelibrary.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.gamelibrary.R
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [signup.newInstance] factory method to
 * create an instance of this fragment.
 */
class signup : Fragment() {

    private val TAG = "Sign up fragment"

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
        val view : View = inflater.inflate(R.layout.fragment_signup, container, false)
        val loginComm = activity as loginComm

        // view references
        val name      : EditText = view.findViewById(R.id.Signup_editTextName)
        val email     : EditText = view.findViewById(R.id.Signup_editTextEmail)
        val password1 : EditText = view.findViewById(R.id.Signup_editTextPassword1)
        val password2 : EditText = view.findViewById(R.id.Signup_editTextPassword2)
        val submit    : Button   = view.findViewById(R.id.Signup_button)
        //val cancel    : Button   = view.findViewById(R.id.Signup_button)

        // submit
        submit.setOnClickListener {

            var data : Triple<String, String, String>

            try {
                data = checkValues(name, email, password1, password2)
                loginComm.commSignup(data.first, data.second, data.third)
            } catch (e : Exception) {
                Log.d(TAG, e.toString())
            }
        }

        // cancel
//        cancel.setOnClickListener {
//
//        }

        return view
    }

    fun checkValues(name : EditText, email : EditText, password1 : EditText, password2 : EditText) : Triple<String, String, String> {

        val nameIn      = name.text.toString()
        val emailIn     = email.text.toString()
        val password1In = password1.text.toString()
        val password2In = password2.text.toString()

        // check for blank values
        if (name.text.isBlank()) {
            name.setError("Name field is empty, please enter a name")
            name.requestFocus()
            throw Exception("name missing")
        }

        if (email.text.isBlank()) {
            email.setError("Email field is empty, please enter an email")
            email.requestFocus()
            throw Exception("email missing")
        }

        if (password1.text.isBlank()) {
            password1.setError("Password field is empty, please enter a password")
            password1.requestFocus()
            throw Exception("password1 missing")
        }

        if (password2In.isBlank()) {
            password2.setError("Password field is empty, please enter a password")
            password2.requestFocus()
            throw Exception("password2 missing")
        }

        // check for mismatched passwords
        if (!password2In.contentEquals(password1In)) {
            password2.setError("Password does not match")
            password2.requestFocus()
            throw Exception("password doesn't match")
        }

        // everything is hunkydory
        return Triple(nameIn, emailIn, password1In)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment signup.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            signup().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}