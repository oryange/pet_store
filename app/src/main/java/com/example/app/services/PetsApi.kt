package com.example.app.services

import com.example.app.services.response.BreedsResponse
import com.example.app.services.response.ByBreedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface PetsApi {
    @GET("breeds/list/all")
    suspend fun getAllBreeds(): Response<BreedsResponse>

    @GET("https://dog.ceo/api/breed/{breed}/images")
    suspend fun getListByBreed(@Path("breed") breed: String): Response<ByBreedResponse>

    @GET("breeds/image/random")
    suspend fun getRandom(): Response<BreedsResponse>

}
