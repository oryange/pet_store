package com.example.app.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.repository.PetsRepository
import com.example.app.utils.Constants
import com.example.app.utils.ResultState
import kotlinx.coroutines.launch

internal class BreedListViewModel(private val petsRepository: PetsRepository) : ViewModel() {

    private val _byBreedsList = MutableLiveData<List<String>>()
    val byBreedsList = _byBreedsList

    fun getListByBreed(breed: String) {
        viewModelScope.launch {
            val response = petsRepository.getListByBreed(breed)
            response.let {
                when (it) {
                    is ResultState.Success -> {
                        _byBreedsList.postValue(it.data.listOfmessage)
                    }
                    is ResultState.Error -> _byBreedsList.postValue(listOf(Constants.DEFAULT_VALUE))
                }
            }
        }
    }
}
