package com.example.androidchallenge.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Image(
    val small: String = "",
    val large: String = ""
) : Parcelable