package com.github.anshul1507.kmm_network_request.repo

import com.github.anshul1507.kmm_network_request.model.MovieResponse

class ModelRepo {
    suspend fun getPopularMoviesData(): MovieResponse {
        return getPopularMovies()
    }
}
