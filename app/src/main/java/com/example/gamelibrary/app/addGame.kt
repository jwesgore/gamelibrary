package com.example.gamelibrary.app

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import com.example.gamelibrary.R
import com.google.android.material.chip.Chip

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [addGame.newInstance] factory method to
 * create an instance of this fragment.
 */
class addGame : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var suggestion = arrayOf(
        "Nintendo Switch", "Nintendo Wii U", "Nintendo Wii", "Nintendo Gamecube",
        "(N64) Nintendo 64", "(SNES) Super Nintendo", "(NES) Nintendo Entertainment System",
        "Xbox Series X", "Xbox One", "Xbox 360", "Xbox",
        "(PS5) Playstation 5","(PS4) Playstation 4","(PS3) Playstation 3",
        "(PS2) Playstation 2","(PS) Playstation")

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
        val view : View = inflater.inflate(R.layout.fragment_add_game, container, false)
        val appComm : appComm = activity as appComm

        val platform : AutoCompleteTextView = view.findViewById(R.id.AddGame_autoCompleteTextViewPlatform)
        val title    : EditText = view.findViewById(R.id.AddGame_editTextTitle)
        val year     : EditText = view.findViewById(R.id.AddGame_editTextYear)
        val add      : Button   = view.findViewById(R.id.AddGame_buttonAdd)
        val cancel   : Button   = view.findViewById(R.id.AddGame_buttonCancel)
        val physical : Chip     = view.findViewById(R.id.AddGame_chipPhysicalGame)
        val digital  : Chip     = view.findViewById(R.id.AddGame_chipDigitalGame)
        val case     : Chip     = view.findViewById(R.id.AddGame_chipCase)
        val manual   : Chip     = view.findViewById(R.id.AddGame_chipManual)

        // autocomplete text
        var adapter = ArrayAdapter(view.context,android.R.layout.simple_list_item_1, suggestion)
        platform.threshold = 0
        platform.setAdapter(adapter)

        // onclick
        add.setOnClickListener {
            try {
                var game: GameFile = checkValues(title, year, platform)
                appComm.addGameFile(game)
            } catch (e : Exception) {
                Log.d(TAG, e.toString())
            }
        }

        cancel.setOnClickListener {  }



        return view
    }

    private fun checkValues(title: EditText, year: EditText, platform: AutoCompleteTextView): GameFile {
        val titleIn    = title.text.toString()
        val yearIn     = year.text.toString()
        val platformIn = platform.text.toString()

        if (titleIn.isBlank()) {
            title.setError("No title entered")
            title.requestFocus()
            throw Exception("no title")
        }

        if (yearIn.isBlank()) {
            year.setError("No year entered")
            year.requestFocus()
            throw Exception("no year")
        }

        if (platformIn.isBlank()) {
            platform.setError("No platform entered")
            platform.requestFocus()
            throw Exception("no platform")
        }

        var game = GameFile(titleIn, yearIn)
        game.platform = platformIn
        return game
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment addGame.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            addGame().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}