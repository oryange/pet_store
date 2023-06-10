package com.example.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app.databinding.ActivityHomeBinding
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.app.repository.PetsRepositoryImpl
import com.example.app.services.RetrofitConfig
import com.example.app.ui.HomeViewModel
import com.example.app.ui.HomeViewModelFactory
import com.example.app.utils.ResultState
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

        binding.randomDog.setOnClickListener {

            onClickRandom()
        }
    }

    private fun onClickRandom() {

        homeViewModel.getRandom()

        homeViewModel._randomDog.observe(this) {
            println("VER AQUI: ${homeViewModel._randomDog.value}")
        }


    }
}
