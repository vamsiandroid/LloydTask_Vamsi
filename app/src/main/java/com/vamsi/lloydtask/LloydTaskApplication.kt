package com.vamsi.lloydtask

import android.app.Application
import com.vamsi.lloydtask.di.ApplicationComponent
import com.vamsi.lloydtask.di.DaggerApplicationComponent


class LloydTaskApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}