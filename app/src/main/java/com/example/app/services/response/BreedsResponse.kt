package com.example.app.services.response

import com.google.gson.annotations.SerializedName

data class BreedsResponse(
    @SerializedName("message")
    val message: String,
)
