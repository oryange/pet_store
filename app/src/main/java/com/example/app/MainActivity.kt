package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app.databinding.ActivityHomeBinding
import androidx.activity.viewModels
import com.example.app.repository.PetsRepositoryImpl
import com.example.app.services.RetrofitConfig
import com.example.app.ui.BreedListActivity
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
            val intent = Intent(this, BreedListActivity::class.java)
            intent.putExtra("breed", "hound")
            startActivity(intent)
        }
    }

    private fun onClickRandom() {
        homeViewModel.getRandom()
        val imageUrl = homeViewModel.randomDog.value
        val randomDogImageView = binding.randomDog
        homeViewModel.randomDog.observe(this) {
            Picasso.get().load(imageUrl).into(randomDogImageView)
        }
    }
}
