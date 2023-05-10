package com.devloper.squad.punkbeer


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReplayConfig(
    val capture: Boolean,
    val imageRetentionTimeInMinutes: Int
)