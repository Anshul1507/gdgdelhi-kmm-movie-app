package com.github.anshul1507.kmm_network_request.android.movie_details

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.github.anshul1507.kmm_network_request.android.databinding.ActivityMovieDetailsBinding
import com.github.anshul1507.kmm_network_request.model.MovieDetails
import com.github.anshul1507.kmm_network_request.repo.posterBaseUrl
import java.text.NumberFormat
import java.util.*

class MovieDetailsActivity : AppCompatActivity() {

    private val viewModel: MovieDetailViewModel by viewModels()

    private val binding by lazy {
        ActivityMovieDetailsBinding.inflate(layoutInflater)
    }

    private lateinit var titleMovie: String
    private lateinit var movieHomePage: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val movieId: Int = intent.getIntExtra("movieID", 1)

        viewModel.getMovieDetails(movieId).observe(this) {
            if (it == null)
                return@observe

            val details = it
            Log.e("test", "details: " + details)

            binding.progressBar.visibility = View.GONE
            binding.parent.visibility = View.VISIBLE

            fillUI(it)
            titleMovie = it.title
            movieHomePage = it.homepage

        }
    }

    private fun fillUI(it: MovieDetails) {
        binding.movieTitle.text = it.title
        binding.movieTagline.text = it.tagline
        binding.movieReleaseDate.text = it.releaseDate
        binding.movieRating.text = it.rating.toString()
        binding.movieRuntime.text = it.runtime.toString() + " mins"
        binding.movieOverview.text = Html.fromHtml(it.overview)

        val formatCur = NumberFormat.getCurrencyInstance(Locale.US)
        binding.movieBudget.text = formatCur.format(it.budget)
        binding.movieRevenue.text = formatCur.format(it.revenue)

        val moviePosterUrl = posterBaseUrl + it.posterPath
        Glide.with(this).load(moviePosterUrl).into(binding.imagePoster)
    }
}