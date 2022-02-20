package com.github.anshul1507.kmm_network_request.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class Model(
    val label: String,
    val list: List<SecondModel>
)

@Serializable
data class SecondModel(

    @SerialName("_id")
    val id: Int,

    @SerialName("heading")
    val name: String
)
