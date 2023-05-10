package com.devloper.squad.punkbeer


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AppConfig(
    val applicationId: String,
    val capture: Int,
    val captureLifecycle: Int,
    val ipMasking: String,
    val replayConfig: ReplayConfig,
    val reportCrashes: Int,
    val reportErrors: Int,
    val trafficControlPercentage: Int
)