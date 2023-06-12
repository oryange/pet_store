package com.example.app.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.repository.dataRemote.PetsRepository
import com.example.app.utils.Constants.DEFAULT_VALUE
import com.example.app.utils.ResultState
import kotlinx.coroutines.launch

internal class HomeViewModel(private val petsRepository: PetsRepository) : ViewModel() {
    private val _randomDog = MutableLiveData<String>()
    private val _allBreedsList = MutableLiveData<List<String>>()

    val randomDog = _randomDog
    val allBreedsList = _allBreedsList

    fun getAllBreeds() {
        viewModelScope.launch {
            val response = petsRepository.getAllBreeds()
            response.let {
                when (it) {
                    is ResultState.Success -> {
                        _allBreedsList.postValue(it.data.messageObject.keys.toList())
                    }
                    is ResultState.Error -> _allBreedsList.postValue(listOf(DEFAULT_VALUE))
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
