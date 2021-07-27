package com.example.androidchallenge.data

import kotlinx.serialization.Serializable

@Serializable
data class Auth(val username: String, val password: String)