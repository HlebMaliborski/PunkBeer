package com.devloper.squad.punkbeer


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RageTapConfig(
    val dispersionRadius: Int,
    val minimumNumberOfTaps: Int,
    val tapDuration: Int,
    val timespanDifference: Int
)