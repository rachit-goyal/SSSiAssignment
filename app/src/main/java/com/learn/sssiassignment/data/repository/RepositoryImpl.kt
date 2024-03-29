package com.learn.sssiassignment.data.repository

import com.learn.sssiassignment.data.local.UserDao
import com.learn.sssiassignment.data.local.UserLocalModel
import com.learn.sssiassignment.data.remote.ApiService
import com.learn.sssiassignment.data.remote.models.UserData
import com.learn.sssiassignment.domain.repository.RepositoryInterface
import com.learn.sssiassignment.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
created by Rachit on 3/27/2024.
 */
class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
) : RepositoryInterface {
    override suspend fun getData(): Resource<UserData> {
        return try {
            Resource.Loading
            val resp = apiService.getList()
            Resource.Success(resp)
        } catch (e: Exception) {
            Resource.Error(e.message)
        }
    }

    override suspend fun insertUser(userLocalModel: UserLocalModel) {
        userDao.insertUser(userLocalModel)
    }

    override  fun getUsers(): Flow<List<UserLocalModel>> {
       return userDao.getAllUsers()
    }
}