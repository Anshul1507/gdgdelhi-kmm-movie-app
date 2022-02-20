package com.github.anshul1507.kmm_network_request.android.movie_details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.anshul1507.kmm_network_request.android.databinding.ActivityMovieDetailsBinding

class MovieDetailsActivity: AppCompatActivity() {

    private val viewModel: MovieDetailViewModel by viewModels()

    private val binding by lazy {
        ActivityMovieDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }

}