package com.github.anshul1507.kmm_network_request.android

import androidx.lifecycle.*
import com.github.anshul1507.kmm_network_request.model.MovieInfo
import com.github.anshul1507.kmm_network_request.repo.ModelRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val modelRepo: ModelRepo = ModelRepo()

    fun getPopularMovies(): LiveData<List<MovieInfo>> {
        val ld: MutableLiveData<List<MovieInfo>> = MutableLiveData<List<MovieInfo>>()

        viewModelScope.launch(Dispatchers.IO) {
            val response = modelRepo.getPopularMoviesData()

            ld.postValue(response.moviesList)

        }
        return ld
    }
}
