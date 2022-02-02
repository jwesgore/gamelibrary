package com.example.gamelibrary.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.gamelibrary.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "game"

/**
 * A simple [Fragment] subclass.
 * Use the [GameDetails.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameDetails : Fragment() {
    // TODO: Rename and change types of parameters
    private var game: GameFile? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            game = it.getSerializable(ARG_PARAM1) as GameFile
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_game_details, container, false)

        val title     : TextView  = view.findViewById(R.id.Details_textViewTitle)
        val year      : TextView  = view.findViewById(R.id.Details_textViewYear)
        val platform  : TextView  = view.findViewById(R.id.Details_textViewPlatform)
        val image     : ImageView = view.findViewById(R.id.Details_imageView)
        val chipGroup : ChipGroup = view.findViewById(R.id.Details_chipGroup)

        title.text    = game?.title
        year.text     = game?.year
        platform.text = game?.platform

        getChips(chipGroup)

        return view
    }

    private fun getChips(chipGroup: ChipGroup) {
        val attributes = game?.attributes

        for (key in attributes?.keys!!) {
            if (attributes[key] == true) {
                val chip = Chip(chipGroup.context)
                chip.text = key
                chipGroup.addView(chip)
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment gameDetails.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            GameDetails().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}