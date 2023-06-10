package com.example.app.repository

import com.example.app.services.PetsApi
import com.example.app.services.response.BreedsResponse
import com.example.app.utils.ResultState

internal class PetsRepositoryImpl(private val petsApi: PetsApi) : PetsRepository {

    override suspend fun getRandom(): ResultState<BreedsResponse> {
        return try {
            val response = petsApi.getRandom()
            if (response.isSuccessful) {
                response.body()?.let {
                    ResultState.Success(it)
                } ?: ResultState.Error("Response body is null")
            } else {
                ResultState.Error("Unsuccessful response from API")
            }
        } catch (e: Exception) {
            ResultState.Error("Error calling API: $e")
        }
    }

    override suspend fun getBreeds(): ResultState<BreedsResponse> {
        return try {
            val response = petsApi.getBreeds()
            if (response.isSuccessful) {
                // todo: verificar !!
                ResultState.Success(response.body()!!)
            } else {
                ResultState.Error("Unsuccessful response Api")
            }
        } catch (e: Exception) {
            ResultState.Error("Error to call Api: $e")
        }
    }
}
