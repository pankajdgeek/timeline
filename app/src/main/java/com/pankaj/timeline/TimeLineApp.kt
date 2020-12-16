package com.pankaj.timeline

import android.app.Application
import com.pankaj.pankaj.util.printInfoLog
import com.pankaj.timeline.di.mainModule
import com.pankaj.timeline.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TimeLineApp : Application() {
    override fun onCreate() {
        super.onCreate()
        printInfoLog(this,"started application")
        startKoin {
            // Android context
            androidContext(this@TimeLineApp)
            // modules
            modules(listOf(mainModule, networkModule))
        }
    }
}