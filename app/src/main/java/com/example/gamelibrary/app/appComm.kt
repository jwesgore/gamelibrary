package com.example.gamelibrary.app

interface appComm {
    fun UpdateRecycler(data : ArrayList<GameFile>)
    fun gotoAddGame()
    fun addGameFile(game: GameFile)
    fun onItemClick(position: Int)
}