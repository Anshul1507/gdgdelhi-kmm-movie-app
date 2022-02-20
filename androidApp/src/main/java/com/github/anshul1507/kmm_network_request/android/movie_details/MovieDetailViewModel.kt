package com.github.anshul1507.kmm_network_request.android.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.anshul1507.kmm_network_request.model.MovieDetails
import com.github.anshul1507.kmm_network_request.repo.ModelRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel : ViewModel() {
    private val modelRepo: ModelRepo = ModelRepo()

    fun getMovieDetails(movieId: Int): LiveData<MovieDetails> {
        val ld: MutableLiveData<MovieDetails> = MutableLiveData<MovieDetails>()

        viewModelScope.launch(Dispatchers.IO) {
            val response = modelRepo.getMovieDetailData(movieId)

            ld.postValue(response)
        }
        return ld
    }
}
