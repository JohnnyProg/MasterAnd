package com.example.mindand.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "players")
data class Player(
    @PrimaryKey(autoGenerate = true)
    val playerId: Long = 0,
    val name: String = "",
    val email: String = ""
)

//ta klasa nie jest encją
data class PlayerWithScore(
    val scoreId: Long,
    val playerId: Long,
    val name: String,
    val email: String,
    val score: Int
    //dodać niezbędne pola...
)
