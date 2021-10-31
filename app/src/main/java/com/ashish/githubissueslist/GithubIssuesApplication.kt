package com.ashish.githubissueslist

import android.app.Application
import com.facebook.stetho.Stetho
import di.adapterModule
import di.repoImplementation
import di.retrofitModule
import di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GithubIssuesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this);
        startKoin {
            androidContext(this@GithubIssuesApplication)
            androidLogger(Level.ERROR)
            modules(
                listOf(
                    adapterModule,
                    viewModelModule,
                    retrofitModule,
                    repoImplementation
                )
            )
        }
    }
}