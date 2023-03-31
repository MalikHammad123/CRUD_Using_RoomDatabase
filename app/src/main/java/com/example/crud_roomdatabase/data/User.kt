package com.example.crud_roomdatabase.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user")
data class User(

    var fname: String="",
    var lname: String="",

): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int=0

}