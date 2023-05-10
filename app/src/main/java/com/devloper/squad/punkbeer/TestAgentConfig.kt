package com.devloper.squad.punkbeer


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TestAgentConfig(
    val appConfig: AppConfig,
    val dynamicConfig: DynamicConfig,
    val mobileAgentConfig: MobileAgentConfig,
    val timestamp: Long
)