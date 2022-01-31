package com.example.gamelibrary.app

import androidx.fragment.app.Fragment

interface appComm {
    fun UpdateRecycler(): Unit
    fun gotoAddGame(): Unit
    fun addGameFile(game: GameFile): Unit
}