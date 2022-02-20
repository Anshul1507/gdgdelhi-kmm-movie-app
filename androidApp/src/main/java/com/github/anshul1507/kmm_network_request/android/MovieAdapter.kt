package com.github.anshul1507.kmm_network_request.android

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.anshul1507.kmm_network_request.android.databinding.ItemPopularMoviesBinding
import com.github.anshul1507.kmm_network_request.android.movie_details.MovieDetailsActivity
import com.github.anshul1507.kmm_network_request.model.MovieDetails
import com.github.anshul1507.kmm_network_request.model.MovieInfo
import com.github.anshul1507.kmm_network_request.repo.posterBaseUrl

class MovieAdapter(private val mList: List<MovieInfo>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemPopularMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieInfo) {
            binding.cvPopularMovieTitle.text = movie.title
            binding.cvPopularMovieReleaseDate.text = movie.releaseDate

            val moviePosterUrl = posterBaseUrl + movie.posterPath
            Glide.with(itemView.context)
                .load(moviePosterUrl)
                .into(binding.cvIvPopularMovie)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, MovieDetailsActivity::class.java)
                intent.putExtra("movieID", movie.id)
                itemView.context.startActivity(intent)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemPopularMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount() = mList.size
}