package com.example.app.ui.breed

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

    private val breedListViewModel: BreedListViewModel by viewModels {
        BreedListWieModelFactory(PetsRepositoryImpl(RetrofitConfig.getApiService()))
    }

    private val binding: ActivityBreedListBinding by lazy {
        ActivityBreedListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getSelectedBreed()


        configRecyclerView()
        configToolbar()
    }

    private fun getSelectedBreed() {
        breed = intent.getStringExtra(breedKey) ?: breedLabrador
    }
    private fun configToolbar() {
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }
    }

    private fun configRecyclerView() {
        binding.progressBar.visibility = View.VISIBLE
        breedListViewModel.getListByBreed(breed)
        var breedList = breedListViewModel.byBreedsList.value
        val adapter = BreedListAdapter(breedList ?: emptyList())
        binding.recyclerBreeds.adapter = adapter

        return breedListViewModel.byBreedsList.observe(this) { list ->
            if (list != null) {
                adapter.setList(list)
                binding.progressBar.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.VISIBLE
            }
        }
    }
}
