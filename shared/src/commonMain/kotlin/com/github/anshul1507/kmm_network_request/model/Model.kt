package com.github.anshul1507.kmm_network_request.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(

    @SerialName("page")
    val page: Int,

    @SerialName("results")
    val moviesList: List<MovieInfo>,

    @SerialName("total_pages")
    val totalPages: Int,

    @SerialName("total_results")
    val totalResults: Int
)

@Serializable
data class MovieInfo(

    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdropPath: String,

    @SerialName("genre_ids")
    val genreIds: List<Int>,

    val id: Int,

    @SerialName("original_language")
    val originalLanguage: String,

    @SerialName("original_title")
    val originalTitle: String,

    val overview: String,

    val popularity: Double,

    @SerialName("poster_path")
    val posterPath: String,

    @SerialName("release_date")
    val releaseDate: String,

    val title: String,

    val video: Boolean,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Int
)


@Serializable
data class MovieDetails(

    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdropPath: String,

    val budget: String,
    val homepage: String,
    val id: String,

    @SerialName("original_title")
    val originalTitle: String,

    val overview: String,

    @SerialName("poster_path")
    val posterPath: String,

    @SerialName("release_date")
    val releaseDate: String,

    val revenue: Long,

    val runtime: Int,

    val status: String,

    val tagline: String,

    val title: String,

    @SerialName("vote_average")
    val rating: Double
)

