package com.example.app.repository

import com.example.app.services.response.BreedsResponse
import com.example.app.services.response.ByBreedResponse
import com.example.app.utils.ResultState

internal interface PetsRepository {
    suspend fun getAllBreeds(): ResultState<BreedsResponse>

    suspend fun getListByBreed(breed: String): ResultState<ByBreedResponse>

    suspend fun getRandom(): ResultState<BreedsResponse>
}
