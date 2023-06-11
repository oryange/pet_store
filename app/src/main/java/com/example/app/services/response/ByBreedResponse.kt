package com.example.app.services.response

import com.google.gson.annotations.SerializedName

data class ByBreedResponse(
    @SerializedName("message")
    val listOfmessage: List<String>,
)
