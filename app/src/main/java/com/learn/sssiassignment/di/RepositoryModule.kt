package com.learn.sssiassignment.di


import com.learn.sssiassignment.data.repository.RepositoryImpl
import com.learn.sssiassignment.domain.repository.RepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
created by Rachit on 1/2/2024.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsRepository(repositoryImpl: RepositoryImpl): RepositoryInterface
}