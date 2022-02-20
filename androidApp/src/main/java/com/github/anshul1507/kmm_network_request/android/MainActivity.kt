package com.github.anshul1507.kmm_network_request.android

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.github.anshul1507.kmm_network_request.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        var PAGE = 1
    }

    private val viewModel: MainViewModel by viewModels()

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val gridLayoutManager = GridLayoutManager(this, 3)
        binding.recyclerViewPopularMovies.layoutManager = gridLayoutManager
        binding.recyclerViewPopularMovies.setHasFixedSize(true)

        loadPageWiseData()

        binding.prevPage.setOnClickListener {
            if (PAGE > 1) {
                PAGE--
                loadPageWiseData()
            } else {
                Toast.makeText(this, "You are on first page", Toast.LENGTH_SHORT).show()
            }
        }

        binding.nextPage.setOnClickListener {
            PAGE++
            loadPageWiseData()
        }
    }

    private fun loadPageWiseData() {
        binding.progressBarPopular.visibility = View.VISIBLE
        binding.prevNextButtons.visibility = View.GONE
        binding.recyclerViewPopularMovies.visibility = View.GONE

        viewModel.getPopularMovies(PAGE).observe(this) {
            if (it.isEmpty())
                return@observe

            val list = it
            Log.e("test", "result: " + list.size)
            val adapter = MovieAdapter(list)
            binding.recyclerViewPopularMovies.adapter = adapter
            binding.pageNo.text = "Page: $PAGE"

            binding.recyclerViewPopularMovies.visibility = View.VISIBLE
            binding.progressBarPopular.visibility = View.GONE
            binding.prevNextButtons.visibility = View.VISIBLE
        }
    }
}
