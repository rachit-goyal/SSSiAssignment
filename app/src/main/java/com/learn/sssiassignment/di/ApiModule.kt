package com.learn.sssiassignment.di

import com.learn.sssiassignment.utils.cons.ApiConstants
import com.learn.sssiassignment.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
created by Rachit on 3/27/2024.
 */

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit():Retrofit{
       return Retrofit.Builder().baseUrl(ApiConstants.BASE_URL + ApiConstants.API).addConverterFactory(
           GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
     return   retrofit.create(ApiService::class.java)
    }
}