package com.example.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.app.databinding.ActivityBreedListBinding
import com.example.app.repository.PetsRepositoryImpl
import com.example.app.services.RetrofitConfig
import com.example.app.utils.Constants.breedKey
import com.example.app.utils.Constants.breedLabrador

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
        //todo: refactor
        breed = intent.getStringExtra(breedKey) ?: breedLabrador
        binding.progressBar.visibility = View.VISIBLE
        configRecyclerView()
        configToolbar()

    }

    private fun configToolbar() {
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // todo ver se ha outro metodo que nao esteja deprecado
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun configRecyclerView() {
        //todo: refatorar para um viemodel separado
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
