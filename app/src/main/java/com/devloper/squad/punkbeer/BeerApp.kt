package com.devloper.squad.punkbeer

import android.app.Application
import com.devloper.squad.feature_beer.di.BeerModule
import com.devloper.squad.punkbeer.di.MainModule

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BeerApp : Application() {
    private val startupModules = listOf(MainModule, BeerModule)

    override fun onCreate() {
        super.onCreate()
        // Privacy settings configured below are only provided
// to allow a quick start with capturing monitoring data.
// This has to be requested from the user
// (e.g. in a privacy settings screen) and the user decision
// has to be applied similar to this example.
/*        Dynatrace.applyUserPrivacyOptions(
            UserPrivacyOptions.builder()
                .withDataCollectionLevel(DataCollectionLevel.USER_BEHAVIOR)
                .withCrashReportingOptedIn(true)
                .withCrashReplayOptedIn(true)
                .build()
        )

        val action = Dynatrace.enterAction("Ura")
        action.leaveAction()*/
        startKoin {
            androidContext(this@BeerApp)
            modules(startupModules)
        }
    }
}