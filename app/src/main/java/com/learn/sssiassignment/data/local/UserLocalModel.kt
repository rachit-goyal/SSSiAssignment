package com.learn.sssiassignment.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
created by Rachit on 3/28/2024.
 */

@Entity(tableName = "UserData")
data class UserLocalModel(


    @ColumnInfo(name = "Uid")
    @PrimaryKey(autoGenerate = true)
    val uId: Int?,

    @ColumnInfo(name = "coins")
    val coins: String,

    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "language")
    val languageName: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "image")
    val profilePic: ByteArray?,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserLocalModel

        if (uId != other.uId) return false
        if (coins != other.coins) return false
        if (id != other.id) return false
        if (languageName != other.languageName) return false
        if (name != other.name) return false
        return profilePic.contentEquals(other.profilePic)
    }

    override fun hashCode(): Int {
        var result = uId ?: 0
        result = 31 * result + coins.hashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + languageName.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + profilePic.contentHashCode()
        return result
    }


}