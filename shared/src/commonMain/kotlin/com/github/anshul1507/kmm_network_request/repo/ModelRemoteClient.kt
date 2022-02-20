package com.github.anshul1507.kmm_network_request.repo

import com.github.anshul1507.kmm_network_request.httpClient
import com.github.anshul1507.kmm_network_request.model.MovieDetails
import com.github.anshul1507.kmm_network_request.model.MovieResponse
import io.ktor.client.request.*


//Make your own API_KEY from https://www.themoviedb.org/settings/api
const val API_KEY = "910cb471f3326152066529eef1b406b2"
const val requestUrl = "https://api.themoviedb.org/3/movie/popular?api_key=$API_KEY"
const val posterBaseUrl = "https://image.tmdb.org/t/p/original/"

suspend fun getPopularMovies(): MovieResponse {
    return httpClient.get(requestUrl)
}

suspend fun getMovieDetail(movieId: Int): MovieDetails {
    val movieDetailUrl = "https://api.themoviedb.org/3/movie/${movieId}?api_key=${API_KEY}"
    return httpClient.get(movieDetailUrl)
}