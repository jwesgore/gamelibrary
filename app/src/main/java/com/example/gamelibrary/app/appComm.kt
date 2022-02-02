package com.example.gamelibrary.app

interface appComm {
    fun UpdateRecycler(data : ArrayList<GameFile>): Unit
    fun gotoAddGame(): Unit
    fun addGameFile(game: GameFile): Unit
    fun onItemClick(position: Int) : Unit
}