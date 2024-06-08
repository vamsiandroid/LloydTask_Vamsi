package com.vamsi.lloydtask.di

import android.content.Context
import androidx.lifecycle.ViewModel
import com.vamsi.lloydtask.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class,ViewModelModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity) // for field inject property inside the MainActivity


    fun getMap(): Map<Class<*>, ViewModel>

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

}