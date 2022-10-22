package com.anku.githubtrending

import android.app.Application
import android.content.Context
import com.anku.githubtrending.di.appModule
import com.anku.githubtrending.di.dbModule
import com.anku.githubtrending.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.util.logging.Level
//the application class where we assign dependency injection
class ApplicationClass: Application() {
    override fun onCreate() {
        super.onCreate()
       // start Koin!
        startKoin {
            androidLogger()
            androidLogger(org.koin.core.logger.Level.ERROR)
            // declare used Android context
            androidContext(this@ApplicationClass)
            // declare modules
            modules(listOf(
                dbModule,
                networkModule,
                appModule
            ))

        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

}