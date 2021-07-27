package com.example.androidchallenge.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Story(
    val name: String = "",
    val category: String = "",
    val image: Image = Image(),
    val date: String = "",
    val text: String = ""
) : Parcelable
