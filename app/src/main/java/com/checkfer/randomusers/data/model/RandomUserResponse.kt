package com.checkfer.randomusers.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

data class RandomUserResponse(val results: List<User>)

@Parcelize
@Keep
data class User(
    val name: Name,
    val email: String,
    val phone: String,
    val picture: ProfilePic,
    val location: Location,
) : Parcelable

@Parcelize
@Keep
data class Name(
    val title: String,
    val first: String,
    val last: String
) : Parcelable

@Parcelize
@Keep
data class ProfilePic(val large: String, val medium: String) : Parcelable

@Parcelize
@Keep
data class Location(val city: String, val country: String) : Parcelable
