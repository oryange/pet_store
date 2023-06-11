package com.example.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.app.databinding.ActivityBreedListBinding
import com.example.app.repository.PetsRepositoryImpl
import com.example.app.services.RetrofitConfig

class BreedListActivity : AppCompatActivity() {

    private lateinit var breed: String

    private val homeViewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(PetsRepositoryImpl(RetrofitConfig.getApiService()))
    }

    private val binding: ActivityBreedListBinding by lazy {
        ActivityBreedListBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        breed = intent.getStringExtra("breed") ?: "labrador"
        binding.progressBar.visibility = View.VISIBLE
        configRecyclerView()
    }

    private fun configRecyclerView() {
        homeViewModel.getListByBreed(breed)

        var breedList = homeViewModel.byBreedsList.value
        val adapter = BreedListAdapter(breedList ?: emptyList())
        binding.recyclerBreeds.adapter = adapter

        return homeViewModel.byBreedsList.observe(this) { list ->
            if (list != null) {
                adapter.setList(list)
                binding.progressBar.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.VISIBLE
            }
        }
    }
}
