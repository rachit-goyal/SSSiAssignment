package com.learn.sssiassignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
created by Rachit on 3/28/2024.
 */

@Database(
    entities = [UserLocalModel::class], version = 1, exportSchema = false
)
abstract class UserDataBase : RoomDatabase() {
    abstract val userDao: UserDao
}