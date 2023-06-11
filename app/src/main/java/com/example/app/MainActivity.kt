package com.example.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app.databinding.ActivityHomeBinding
import androidx.activity.viewModels
import com.example.app.repository.PetsRepositoryImpl
import com.example.app.services.RetrofitConfig
import com.example.app.ui.HomeViewModel
import com.example.app.ui.HomeViewModelFactory
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(PetsRepositoryImpl(RetrofitConfig.getApiService()))
    }

    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.randomDog.setOnClickListener { onClickRandom() }
        binding.labrador.setOnClickListener { onClickBreeds() }
    }

   private fun onClickBreeds() {
        binding.labrador.setOnClickListener {
            homeViewModel.getListByBreed("hound")
            // levar para tela labrador
        }
    }

    private fun onClickRandom() {
        homeViewModel.getRandom()
        val imageUrl = homeViewModel._randomDog.value
        val randomDogImageView = binding.randomDog
        homeViewModel._randomDog.observe(this) {
            Picasso.get().load(imageUrl).into(randomDogImageView)
        }
    }
}
