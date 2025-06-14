package com.target.targetcasestudy

import android.app.Application
import android.content.Context
import com.target.targetcasestudy.di.appModule
import org.koin.core.context.startKoin

class TargetCaseStudyApplication: Application() {
    companion object {
        private lateinit var applicationContext: Context

        fun getApplicationContext(): Context {
            return applicationContext
        }
    }
    override fun onCreate() {
        super.onCreate()
        TargetCaseStudyApplication.applicationContext = applicationContext
        startKoin {
            modules(appModule)
        }
    }
}