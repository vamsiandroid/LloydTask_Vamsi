package com.vamsi.lloydtask.di

import androidx.lifecycle.ViewModel
import com.vamsi.lloydtask.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @ClassKey(MainViewModel::class)
    @IntoMap
    abstract fun  mainViewModel(mainViewModel: MainViewModel):ViewModel

}