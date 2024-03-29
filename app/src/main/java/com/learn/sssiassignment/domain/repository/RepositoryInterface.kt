package com.learn.sssiassignment.domain.repository

import com.learn.sssiassignment.data.remote.models.UserData
import com.learn.sssiassignment.data.local.UserLocalModel
import com.learn.sssiassignment.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
created by Rachit on 3/27/2024.
 */
interface RepositoryInterface {
    suspend fun getData(): Resource<UserData>
    suspend fun insertUser(userLocalModel: UserLocalModel)
     fun getUsers(): Flow<List<UserLocalModel>>
}