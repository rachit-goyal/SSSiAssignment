package com.learn.sssiassignment.di

import android.content.Context
import androidx.room.Room
import com.learn.sssiassignment.data.local.UserDao
import com.learn.sssiassignment.data.local.UserDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
created by Rachit on 1/2/2024.
 */

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {


    @Provides
    @Singleton
    fun provideDao(noteDatabase: UserDataBase): UserDao {
        return noteDatabase.userDao
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): UserDataBase = Room.databaseBuilder(
        context,
        UserDataBase::class.java,
        "users_db"
    ).build()
}