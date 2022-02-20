package com.github.anshul1507.kmm_network_request.android

import androidx.lifecycle.*
import com.github.anshul1507.kmm_network_request.model.SecondModel
import com.github.anshul1507.kmm_network_request.repo.ModelRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val modelRepo: ModelRepo = ModelRepo()

    fun getModelData(): LiveData<List<SecondModel>> {
        val ld: MutableLiveData<List<SecondModel>> = MutableLiveData<List<SecondModel>>()

        viewModelScope.launch(Dispatchers.IO) {
            val response = modelRepo.getListData()

            ld.postValue(response.list)

        }
        return ld
    }
}
