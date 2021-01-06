package com.mbobiosio.countries

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.mbobiosio.countries.util.ReleaseTree
import timber.log.Timber

class App : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance : App? = null
        fun appContext() : App {
            return instance as App
        }
    }

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