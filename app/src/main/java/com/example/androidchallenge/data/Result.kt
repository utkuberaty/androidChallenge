package com.example.androidchallenge.data

import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val isBannerEnabled: Boolean = true,
    val meditations: List<Meditation>,
    val stories: List<Story>
)