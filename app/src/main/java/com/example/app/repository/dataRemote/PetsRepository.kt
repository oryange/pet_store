package com.example.app.repository.dataRemote

import com.example.app.services.response.AllBreedsResponse
import com.example.app.services.response.BreedsResponse
import com.example.app.services.response.ByBreedResponse
import com.example.app.utils.ResultState

internal interface PetsRepository {
    suspend fun getAllBreeds(): ResultState<AllBreedsResponse>

    suspend fun getListByBreed(breed: String): ResultState<ByBreedResponse>

    suspend fun getRandom(): ResultState<BreedsResponse>
}
