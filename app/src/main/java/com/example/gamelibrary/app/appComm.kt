package com.example.gamelibrary.app

interface appComm {
    fun UpdateRecycler(): Unit
    fun gotoAddGame(): Unit
    fun addGameFile(game: GameFile): Unit
}