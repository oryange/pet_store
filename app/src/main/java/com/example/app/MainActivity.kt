package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.app.databinding.ActivityHomeBinding
import androidx.activity.viewModels
import com.example.app.repository.PetsRepositoryImpl
import com.example.app.services.RetrofitConfig
import com.example.app.ui.BreedListActivity
import com.example.app.ui.HomeViewModel
import com.example.app.ui.HomeViewModelFactory
import com.example.app.utils.Constants.breedAkita
import com.example.app.utils.Constants.breedBulldog
import com.example.app.utils.Constants.breedHound
import com.example.app.utils.Constants.breedKey
import com.example.app.utils.Constants.breedLabrador
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private val spinnerValues = mutableListOf<String>()
    private lateinit var spinnerAdapter: ArrayAdapter<String>

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

        // Inicializa o adaptador do Spinner
        spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerValues)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Associa o adaptador ao Spinner
        val spinner = binding.actionBarSpinner
        spinner.adapter = spinnerAdapter

        // Supondo que você tenha recebido os valores da API em uma lista chamada "apiValues"
        homeViewModel.getAllBreeds()
        homeViewModel.allBreedsList.observe(this) { list ->
            if (list != null) {
                spinnerValues.addAll(list)
            }
        }
        spinnerAdapter.notifyDataSetChanged()

    }

    private fun setListeners() {
        binding.randomDog.setOnClickListener { onClickRandom() }
        binding.labrador.setOnClickListener { onClickBreeds(breedLabrador) }
        binding.akita.setOnClickListener { onClickBreeds(breedAkita)}
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
