package com.example.gamelibrary.app

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamelibrary.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [home.newInstance] factory method to
 * create an instance of this fragment.
 */
class home : Fragment() {
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
        val view : View = inflater.inflate(R.layout.fragment_home, container, false)
        val appComm : appComm = activity as appComm

        // initialize views
        val recyclerView : RecyclerView = view.findViewById(R.id.Home_recyclerView)
        val button       : FloatingActionButton = view.findViewById(R.id.Home_floatingActionButton)

        button.setOnClickListener {
            appComm.gotoAddGame()
        }

        // recycler view stuff
        var data : ArrayList<GameFile> = ArrayList()
        var recyclerAdapter = recyclerAdapter(data)

        recyclerAdapter.setOnItemClick(appComm)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerAdapter
        }


        getData(data, appComm)

        return view
    }

    private fun getData(data: ArrayList<GameFile>, appComm: appComm) {
        val db   = Firebase.firestore
        db.collection("switch").get().addOnSuccessListener {
            DocumentSnapshot ->
                for (snapshot in DocumentSnapshot) {
                    Log.d(TAG, snapshot.toString())
                    val gameFile = GameFile(snapshot["title"].toString(), snapshot["year"].toString())
                    gameFile.platform    = snapshot["platform"].toString()
                    gameFile.platformAbv = snapshot["platformAbv"].toString()
                    data.add(gameFile)
                }
            appComm.UpdateRecycler(data)
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}