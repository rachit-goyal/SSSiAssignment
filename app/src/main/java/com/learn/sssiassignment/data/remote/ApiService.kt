package com.learn.sssiassignment.data.remote

import com.learn.sssiassignment.data.remote.models.UserData
import com.learn.sssiassignment.utils.cons.ApiConstants
import retrofit2.http.GET

/**
created by Rachit on 3/27/2024.
 */
interface ApiService {
    @GET(ApiConstants.API_END_POINT)
    suspend fun getList(): UserData
}