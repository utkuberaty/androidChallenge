package com.example.androidchallenge.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Meditation(
    val title: String = "",
    val subtitle: String = "",
    val image: Image = Image(),
    val releaseDate: String = "",
    val content: String = ""
) : Parcelable

