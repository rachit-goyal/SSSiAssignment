package com.learn.sssiassignment.utils

/**
created by Rachit on 1/2/2024.
 */
sealed class Resource<out T> {

    data object Loading : Resource<Nothing>()

    data class Error(val error: String?) : Resource<Nothing>()

    data class Success<T>(val data: T ) : Resource<T>()
}