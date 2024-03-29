package com.learn.sssiassignment.data.remote.models


import com.google.gson.annotations.SerializedName

data class UserDataX(
    @SerializedName("coins")
    val coins: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("language_name")
    val languageName: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_pic")
    val profilePic: String?,
)