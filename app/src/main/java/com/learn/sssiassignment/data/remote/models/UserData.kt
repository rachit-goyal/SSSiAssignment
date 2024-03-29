package com.learn.sssiassignment.data.remote.models


import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("code")
    val code: Int,
    @SerializedName("length")
    val length: Int,
    @SerializedName("user_data")
    val userData: List<UserDataX>
)