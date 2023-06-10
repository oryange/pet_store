package com.example.app.ui

import android.content.ClipData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.repository.PetsRepository
import com.example.app.services.response.BreedsResponse
import com.example.app.utils.Constants.DEFAULT_VALUE
import com.example.app.utils.ResultState
import kotlinx.coroutines.launch

internal class HomeViewModel(private val petsRepository: PetsRepository) : ViewModel() {
    private val breedsList = MutableLiveData<BreedsResponse?>()

    // TODO: verificar se de fato precisa ser um liveData este:
    private val randomDog = MutableLiveData<String?>()

    val _breedsList = breedsList
    val _randomDog = randomDog

    fun getRandom() {
        viewModelScope.launch {
            val response = petsRepository.getRandom()
            response?.let {
                when (it) {
                    is ResultState.Success -> randomDog.postValue(it.data.message)
                    is ResultState.Error -> randomDog.postValue(DEFAULT_VALUE)
                }
            }
        }
    }

    suspend fun getBreeds() {
        viewModelScope.launch {
            val response = petsRepository.getBreeds()
            response?.let {
                when (it) {
                    is ResultState.Success -> {
                        breedsList.postValue(it.data)
                    }
                    is ResultState.Error -> {
                        breedsList.postValue(null)
                    }
                }
            }
        }
    }
}
