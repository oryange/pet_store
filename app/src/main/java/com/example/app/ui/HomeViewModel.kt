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
    private val randomDog = MutableLiveData<String?>()
    private val allBreedsList = MutableLiveData<BreedsResponse?>()
    private val byBreedsList = MutableLiveData<List<String?>?>()

    val _randomDog = randomDog
    val _allBreedsList = allBreedsList
    val _byBreedsList = byBreedsList

    fun getListByBreed(breed: String) {
        viewModelScope.launch {
            val response = petsRepository.getListByBreed(breed)
            response.let {
                when (it) {
                    is ResultState.Success ->{
                        byBreedsList.postValue(it.data.listOfmessage)
                    }
                    is ResultState.Error -> byBreedsList.postValue(listOf(DEFAULT_VALUE))
                }
            }
        }
    }
    fun getRandom() {
        viewModelScope.launch {
            val response = petsRepository.getRandom()
            response.let {
                when (it) {
                    is ResultState.Success -> randomDog.postValue(it.data.message)
                    is ResultState.Error -> randomDog.postValue(DEFAULT_VALUE)
                }
            }
        }
    }
}
