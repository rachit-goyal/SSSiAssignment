package com.learn.sssiassignment.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
created by Rachit on 3/28/2024.
 */

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userLocalModel: UserLocalModel)

    @Query("select * from userdata order by Uid desc ")
    fun getAllUsers(): Flow<List<UserLocalModel>>

    @Query("delete from userdata where Uid=:id")
    suspend fun delete(id: Int)

}