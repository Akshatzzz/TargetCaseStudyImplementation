package com.target.targetcasestudy

import android.app.Application
import com.target.targetcasestudy.di.appModule
import org.koin.core.context.startKoin

class TargetCaseStudyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}