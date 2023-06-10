package com.example.app.repository

import com.example.app.services.response.BreedsResponse
import com.example.app.utils.ResultState

internal interface PetsRepository {
    suspend fun getRandom(): ResultState<BreedsResponse>
    suspend fun getBreeds(): ResultState<BreedsResponse>
}
