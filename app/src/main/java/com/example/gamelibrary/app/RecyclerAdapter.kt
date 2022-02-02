package com.example.gamelibrary.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamelibrary.R

class RecyclerAdapter(private val data : ArrayList<GameFile>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private lateinit var appComm: appComm

    fun setOnItemClick(listener : appComm) {
        appComm = listener
    }

    // build abstract holder
    class ViewHolder(view: View, listener: appComm) : RecyclerView.ViewHolder(view) {

        // fill holder with references to views
        val title    : TextView = view.findViewById(R.id.List_textViewTitle)
        val year     : TextView = view.findViewById(R.id.List_textViewYear)
        val platform : TextView = view.findViewById(R.id.List_textViewPlatform)
        val image    : ImageView = view.findViewById(R.id.List_imageView)

        // initialize references
        init {

            view.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list, parent, false)
        return ViewHolder(view, appComm)
    }

    // populate holder with values
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game : GameFile = data[position]

        holder.title.text    = game.title
        holder.year.text     = game.year
        holder.platform.text = game.platformAbv
        //holder.platform.text = game.platform

    }

    override fun getItemCount(): Int {
        return data.size
    }
}