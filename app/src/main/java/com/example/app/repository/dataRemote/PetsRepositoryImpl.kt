package com.example.app.repository.dataRemote

import com.example.app.services.PetsApi
import com.example.app.services.response.AllBreedsResponse
import com.example.app.services.response.BreedsResponse
import com.example.app.services.response.ByBreedResponse
import com.example.app.utils.ResultState

internal class PetsRepositoryImpl(private val petsApi: PetsApi) : PetsRepository {
    override suspend fun getAllBreeds(): ResultState<AllBreedsResponse> {
        return try {
            val response = petsApi.getAllBreeds()
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

    override suspend fun getListByBreed(breed: String): ResultState<ByBreedResponse> {
        return try {
            val response = petsApi.getListByBreed(breed)
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
}
