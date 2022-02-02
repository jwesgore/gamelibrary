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
 * Use the [AddGame.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddGame : Fragment() {
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
    ): View {
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
                game.platformAbv = getAbv(game.platform)
                appComm.addGameFile(game)
            } catch (e : Exception) {
                Log.d(TAG, e.toString())
            }
        }

        cancel.setOnClickListener {  }

        return view
    }

    // get abreviation
    private fun getAbv(platform: String): String {

        // retrieve abv from brackets if it exists
        if (platform.contains('(') && platform.contains(')')) {
            var abv = platform.substringBefore(')')
            abv = abv.substringAfter('(')
            return abv
        }

        // otherwise just return the platform
        return platform
    }

     // check input for errors and return gamefile
    private fun checkValues(title: EditText, year: EditText, platform: AutoCompleteTextView): GameFile {

         // extract input values
        val titleIn    = title.text.toString()
        val yearIn     = year.text.toString()
        val platformIn = platform.text.toString()

         // check for blank input
        if (titleIn.isBlank()) {
            title.error = "No title entered"
            title.requestFocus()
            throw Exception("no title")
        }

        if (yearIn.isBlank()) {
            year.error = "No year entered"
            year.requestFocus()
            throw Exception("no year")
        }

        if (platformIn.isBlank()) {
            platform.error = "No platform entered"
            platform.requestFocus()
            throw Exception("no platform")
        }

         // build and return gamefile
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
            AddGame().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}