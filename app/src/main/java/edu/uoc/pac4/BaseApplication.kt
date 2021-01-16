package edu.uoc.pac4

import android.app.Application
import edu.uoc.pac4.data.di.dataModule
import edu.uoc.pac4.ui.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin!
        startKoin {
            // Declare used Android Context
            androidContext(this@BaseApplication)
            // Declare modules
            modules(dataModule, uiModule)
        }
    }
}