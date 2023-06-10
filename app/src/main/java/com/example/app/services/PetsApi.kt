package com.example.app.services

import com.example.app.services.response.BreedsResponse
import retrofit2.Response
import retrofit2.http.GET

internal interface PetsApi {
    @GET("breeds/list/all")
    suspend fun getBreeds(): Response<BreedsResponse>

    @GET("breeds/image/random")
    suspend fun getRandom(): Response<BreedsResponse>
}
