package com.devloper.squad.punkbeer


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DynamicConfig(
    val serverId: Int,
    val status: String,
    val switchServer: Boolean
)