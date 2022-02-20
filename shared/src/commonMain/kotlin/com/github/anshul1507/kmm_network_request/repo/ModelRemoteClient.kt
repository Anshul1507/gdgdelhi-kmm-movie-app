package com.github.anshul1507.kmm_network_request.repo

import com.github.anshul1507.kmm_network_request.httpClient
import com.github.anshul1507.kmm_network_request.model.Model
import io.ktor.client.request.*


const val requestUrl = "https:www.google.com"

suspend fun getData(): Model {
    return httpClient.get(requestUrl)
}