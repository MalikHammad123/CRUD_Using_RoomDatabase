package com.example.crud_roomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user:User)

    @Query("SELECT * FROM user ")
    suspend fun getAllUser():List<User>

    @Update
    suspend fun updateUsers(users:User)

    @Delete
    suspend fun deleteUsers(users: User)



}