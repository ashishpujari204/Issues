package com.ashish.githubissueslist

import android.app.Application
import di.adapterModule
import di.repoImplementation
import di.retrofitModule
import di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.unloadKoinModules
import org.koin.core.logger.Level
import org.koin.core.module.Module

class GithubIssuesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
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

    internal fun loadModules(module: List<Module>, block: () -> Unit) {
        loadKoinModules(module)
        block()
        unloadKoinModules(module)
    }
}