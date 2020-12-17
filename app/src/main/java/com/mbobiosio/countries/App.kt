package com.mbobiosio.countries

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.mbobiosio.countries.util.ReleaseTree
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        initLog()
    }

    private fun initLog() {
        when {
            BuildConfig.DEBUG -> Timber.plant(Timber.DebugTree())
            else -> Timber.plant(ReleaseTree())
        }
    }

}