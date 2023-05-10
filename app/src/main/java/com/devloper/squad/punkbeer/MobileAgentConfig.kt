package com.devloper.squad.punkbeer


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MobileAgentConfig(
    val maxBeaconSizeKb: Int,
    val maxCachedCrashesCount: Int,
    val maxEventsPerSession: Int,
    val maxSessionDurationMins: Int,
    val rageTapConfig: RageTapConfig,
    val replayConfig: ReplayConfigX,
    val selfmonitoring: Boolean,
    val sendIntervalSec: Int,
    val sessionTimeoutSec: Int,
    val visitStoreVersion: Int
)