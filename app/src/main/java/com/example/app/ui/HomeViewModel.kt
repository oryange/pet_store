package com.example.app.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.repository.PetsRepository
import com.example.app.services.response.BreedsResponse
import com.example.app.utils.Constants.DEFAULT_VALUE
import com.example.app.utils.ResultState
import kotlinx.coroutines.launch

internal class HomeViewModel(private val petsRepository: PetsRepository) : ViewModel() {
    private val _randomDog = MutableLiveData<String>()
    private val _allBreedsList = MutableLiveData<List<String>>()
    private val _byBreedsList = MutableLiveData<List<String>>()

    val randomDog = _randomDog
    val allBreedsList = _allBreedsList
    val byBreedsList = _byBreedsList

    fun getAllBreeds() {
        viewModelScope.launch {
            val response = petsRepository.getAllBreeds()
            response.let {
                when (it) {
                    is ResultState.Success -> {
                        _allBreedsList.postValue(it.data.message.keys.toList())
                    }
                    is ResultState.Error -> _byBreedsList.postValue(listOf(DEFAULT_VALUE))
                }
            }
        }
    }

    fun getListByBreed(breed: String) {
        viewModelScope.launch {
            val response = petsRepository.getListByBreed(breed)
            response.let {
                when (it) {
                    is ResultState.Success -> {
                        _byBreedsList.postValue(it.data.listOfmessage)
                    }
                    is ResultState.Error -> _byBreedsList.postValue(listOf(DEFAULT_VALUE))
                }
            }
        }
    }

    fun getRandom() {
        viewModelScope.launch {
            val response = petsRepository.getRandom()
            response.let {
                when (it) {
                    is ResultState.Success -> _randomDog.postValue(it.data.message)
                    is ResultState.Error -> _randomDog.postValue(DEFAULT_VALUE)
                }
            }
        }
    }
}
