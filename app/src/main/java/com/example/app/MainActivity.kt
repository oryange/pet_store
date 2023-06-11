package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.app.databinding.ActivityHomeBinding
import androidx.activity.viewModels
import com.example.app.repository.PetsRepositoryImpl
import com.example.app.services.RetrofitConfig
import com.example.app.ui.breed.BreedListActivity
import com.example.app.ui.home.HomeViewModel
import com.example.app.ui.home.HomeViewModelFactory
import com.example.app.utils.Constants.breedAkita
import com.example.app.utils.Constants.breedBulldog
import com.example.app.utils.Constants.breedHound
import com.example.app.utils.Constants.breedKey
import com.example.app.utils.Constants.breedLabrador
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private val spinnerValues = mutableListOf<String>()
    private lateinit var spinnerAdapter: ArrayAdapter<String>
    private var isFirstSelection = true

    private val homeViewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(PetsRepositoryImpl(RetrofitConfig.getApiService()))
    }

    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setListeners()
        setupSpinner()
    }

    private fun setupSpinner() {
        spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerValues)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinner = binding.actionBarSpinner
        spinner.adapter = spinnerAdapter

        getBreedListForSpinner()
        itemSelectorConfiguration()
    }

    private fun getBreedListForSpinner() {
        homeViewModel.getAllBreeds()
        homeViewModel.allBreedsList.observe(this) { list ->
            if (list != null) {
                spinnerValues.addAll(list)
                spinnerAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun itemSelectorConfiguration() {
        val spinner = binding.actionBarSpinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (isFirstSelection) {
                    isFirstSelection = false
                    return
                }
                val selectedValue = spinnerValues[position]
                onClickBreeds(selectedValue)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Not implemented
            }
        }
    }

    private fun setListeners() {
        binding.randomDog.setOnClickListener { onClickRandom() }
        binding.labrador.setOnClickListener { onClickBreeds(breedLabrador) }
        binding.akita.setOnClickListener { onClickBreeds(breedAkita) }
        binding.bulldog.setOnClickListener { onClickBreeds(breedBulldog) }
        binding.hound.setOnClickListener { onClickBreeds(breedHound) }
    }

    private fun onClickBreeds(breed: String) {
        val intent = Intent(this, BreedListActivity::class.java)
        intent.putExtra(breedKey, breed)
        startActivity(intent)
    }

    private fun onClickRandom() {
        homeViewModel.getRandom()
        val randomDogImageView = binding.randomDog
        homeViewModel.randomDog.observe(this) { breed ->
            if (breed != null) {
                binding.progressBar.visibility = View.GONE
                binding.randomDog.visibility = View.VISIBLE
                Picasso.get().load(breed).into(randomDogImageView)
            } else {
                binding.progressBar.visibility = View.VISIBLE
                binding.randomDog.visibility = View.GONE
            }
        }
    }
}
