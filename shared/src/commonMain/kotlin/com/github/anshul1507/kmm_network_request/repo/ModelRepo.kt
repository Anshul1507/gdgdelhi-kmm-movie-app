package com.github.anshul1507.kmm_network_request.repo

import com.github.anshul1507.kmm_network_request.model.Model

class ModelRepo {
    suspend fun getListData(): Model {
        return getData()
    }
}
