package com.example.app.ui.breed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.repository.dataLocal.PetsSharedPreferences
import com.example.app.repository.dataRemote.PetsRepository
import com.example.app.services.response.MessageData
import com.example.app.utils.ResultState
import kotlinx.coroutines.launch

internal class BreedListViewModel(
    private val petsRepository: PetsRepository,
    private val petsSharedPreferences: PetsSharedPreferences
) : ViewModel() {

    private val _byBreedsList = MutableLiveData<List<MessageData>>()
    val byBreedsList = _byBreedsList

    fun getListByBreed(breed: String) {
        viewModelScope.launch {
            val response = petsRepository.getListByBreed(breed)
            response.let {
                when (it) {
                    is ResultState.Success -> {
                        _byBreedsList.postValue(it.data.listOfmessage.map { item ->
                            MessageData(item, getFavorite().contains(item))
                        })
                    }
                    is ResultState.Error -> _byBreedsList.postValue(emptyList())
                }
            }
        }
    }

    fun setFavoriteItem(newItem: String) {
        if (getFavorite().contains(newItem)) removeFavorite(newItem)
        else {
            addFavorite(newItem)
        }
        verifyIsFavorite()
    }

     fun verifyIsFavorite() {
        _byBreedsList.value?.let { it ->
            _byBreedsList.value = it.onEach {
                it.isFavorite = getFavorite().contains(it.message)
            }
        }
    }

    private fun addFavorite(newItem: String): Boolean = petsSharedPreferences.addFavorite(newItem)

    private fun removeFavorite(itemToRemove: String): Boolean =
        petsSharedPreferences.removeFavorite(itemToRemove)

    private fun getFavorite(): List<String> = petsSharedPreferences.getFavorite()
}
