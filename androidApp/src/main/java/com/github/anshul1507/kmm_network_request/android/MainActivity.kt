package com.github.anshul1507.kmm_network_request.android

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.github.anshul1507.kmm_network_request.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

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

        viewModel.getPopularMovies().observe(this) {
            if (it.isEmpty())
                return@observe

            val list = it
            Log.e("test", "result: " + list.size)
            val adapter = MovieAdapter(list)
            binding.recyclerViewPopularMovies.adapter = adapter
            binding.recyclerViewPopularMovies.visibility = View.VISIBLE
            binding.progressBarPopular.visibility = View.GONE
        }
    }
}
