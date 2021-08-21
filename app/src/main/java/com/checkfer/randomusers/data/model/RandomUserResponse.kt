package com.checkfer.randomusers.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

data class RandomUserResponse(val results: List<User>)

@Parcelize
@Keep
data class User(
    val email: String,
    val phone: String,
): Parcelable
