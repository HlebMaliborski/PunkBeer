package com.devloper.squad.punkbeer


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReplayConfigX(
    val protocolVersion: Int,
    val selfmonitoring: Int
)