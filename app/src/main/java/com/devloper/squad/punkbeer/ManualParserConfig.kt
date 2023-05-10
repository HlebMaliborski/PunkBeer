package com.devloper.squad.punkbeer

import com.squareup.moshi.JsonReader

/**
 *
 * @author gleb.maliborsky
 */
class ManualParserConfig {

    fun parse(reader: JsonReader): TestAgentConfig {
        val mobileConfig = getMobileAgentConfigObject(reader)
        val appConfig = getAppConfigObject(reader)
        val dynamicConfig = getDynamicConfigObject(reader)
        var timestamp = 0L
        if ("timestamp" == reader.nextName()) {
            timestamp = reader.nextLong()
        }

        reader.endObject()
        reader.peek()
        return TestAgentConfig(appConfig, dynamicConfig, mobileConfig, timestamp)
    }

    fun getDynamicConfigObject(reader: JsonReader): DynamicConfig {
        var serverId = -1
        var switchServer = false
        var status = ""

        val a = reader.nextName()
        reader.beginObject()
        if ("serverId" == reader.nextName()) {
            serverId = reader.nextInt()
        }

        if ("switchServer" == reader.nextName()) {
            switchServer = reader.nextBoolean()
        }

        if ("status" == reader.nextName()) {
            status = reader.nextString()
        }

        reader.endObject()

        return DynamicConfig(
            serverId,
            status,
            switchServer,
        )
    }

    fun getAppConfigObject(reader: JsonReader): AppConfig {
        var capture = -1
        var captureLifecycle = -1
        var reportCrashes = -1
        var reportErrors = -1
        var trafficControlPercentage = -1
        var replayConfig: ReplayConfig? = null
        var ipMasking = ""
        var applicationId = ""

        val a = reader.nextName()
        reader.beginObject()
        if ("capture" == reader.nextName()) {
            capture = reader.nextInt()
        }

        if ("captureLifecycle" == reader.nextName()) {
            captureLifecycle = reader.nextInt()
        }

        if ("reportCrashes" == reader.nextName()) {
            reportCrashes = reader.nextInt()
        }

        if ("reportErrors" == reader.nextName()) {
            reportErrors = reader.nextInt()
        }

        if ("trafficControlPercentage" == reader.nextName()) {
            trafficControlPercentage = reader.nextInt()
        }

        if ("replayConfig" == reader.nextName()) {
            replayConfig = getReplayConfigObject(reader)
        }
        if ("ipMasking" == reader.nextName()) {
            ipMasking = reader.nextString()
        }
        if ("applicationId" == reader.nextName()) {
            applicationId = reader.nextString()
        }

        reader.endObject()

        return AppConfig(
            applicationId,
            capture,
            captureLifecycle,
            ipMasking,
            replayConfig!!,
            reportCrashes,
            reportErrors,
            trafficControlPercentage,
        )
    }

    fun getReplayConfigObject(reader: JsonReader): ReplayConfig {
        var capture = false
        var imageRetentionTimeInMinutes = -1
        reader.beginObject()
        if ("capture" == reader.nextName()) {
            capture = reader.nextBoolean()
        }

        if ("imageRetentionTimeInMinutes" == reader.nextName()) {
            imageRetentionTimeInMinutes = reader.nextInt()
        }

        reader.endObject()
        return ReplayConfig(capture, imageRetentionTimeInMinutes)
    }

    fun getMobileAgentConfigObject(reader: JsonReader): MobileAgentConfig {
        var maxBeaconSizeKb = -1
        var maxCachedCrashesCount = -1
        var maxEventsPerSession = -1
        var maxSessionDurationMins = -1
        var rageTapConfig: RageTapConfig? = null
        var replayConfig: ReplayConfigX? = null
        var selfmonitoring = false
        var sendIntervalSec = -1
        var sessionTimeoutSec = -1
        var visitStoreVersion = -1

        reader.beginObject()
        val agentConfig = reader.nextName()
        reader.beginObject()
        if ("maxBeaconSizeKb" == reader.nextName()) {
            maxBeaconSizeKb = reader.nextInt()
        }

        if ("selfmonitoring" == reader.nextName()) {
            selfmonitoring = reader.nextBoolean()
        }

        if ("maxSessionDurationMins" == reader.nextName()) {
            maxSessionDurationMins = reader.nextInt()
        }

        if ("maxEventsPerSession" == reader.nextName()) {
            maxEventsPerSession = reader.nextInt()
        }

        if ("sessionTimeoutSec" == reader.nextName()) {
            sessionTimeoutSec = reader.nextInt()
        }

        if ("sendIntervalSec" == reader.nextName()) {
            sendIntervalSec = reader.nextInt()
        }

        if ("visitStoreVersion" == reader.nextName()) {
            visitStoreVersion = reader.nextInt()
        }

        if ("maxCachedCrashesCount" == reader.nextName()) {
            maxCachedCrashesCount = reader.nextInt()
        }

        if ("rageTapConfig" == reader.nextName()) {
            rageTapConfig = getRageTapConfigObject(reader)
        }

        if ("replayConfig" == reader.nextName()) {
            replayConfig = getReplayConfigXObject(reader)
        }

        reader.endObject()

        return MobileAgentConfig(
            maxBeaconSizeKb,
            maxCachedCrashesCount,
            maxEventsPerSession,
            maxSessionDurationMins,
            rageTapConfig!!,
            replayConfig!!,
            selfmonitoring,
            sendIntervalSec,
            sessionTimeoutSec,
            visitStoreVersion
        )
    }

    fun getRageTapConfigObject(reader: JsonReader): RageTapConfig {
        var dispersionRadius = -1
        var minimumNumberOfTaps = -1
        var tapDuration = -1
        var timespanDifference = -1
        reader.beginObject()

        if ("tapDuration" == reader.nextName()) {
            tapDuration = reader.nextInt()
        }

        if ("dispersionRadius" == reader.nextName()) {
            dispersionRadius = reader.nextInt()
        }

        if ("timespanDifference" == reader.nextName()) {
            timespanDifference = reader.nextInt()
        }

        if ("minimumNumberOfTaps" == reader.nextName()) {
            minimumNumberOfTaps = reader.nextInt()
        }

        reader.endObject()

        return RageTapConfig(dispersionRadius, minimumNumberOfTaps, tapDuration, timespanDifference)
    }

    fun getReplayConfigXObject(reader: JsonReader): ReplayConfigX {
        var protocolVersion = -1
        var selfmonitoring = -1
        reader.beginObject()
        if ("protocolVersion" == reader.nextName()) {
            protocolVersion = reader.nextInt()
        }

        if ("selfmonitoring" == reader.nextName()) {
            selfmonitoring = reader.nextInt()
        }

        reader.endObject()
        return ReplayConfigX(protocolVersion, selfmonitoring)
    }
}
