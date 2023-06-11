package com.example.app.services.response

import com.google.gson.annotations.SerializedName

data class AllBreedsResponse(
    @SerializedName("message")
    val messageObject: Map<String, Any>
)
