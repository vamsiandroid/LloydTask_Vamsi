package com.vamsi.lloydtask.di

import android.content.Context
import androidx.room.Room
import com.vamsi.lloydtask.db.CountryDB

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideCountryDB(context: Context): CountryDB {
        return Room.databaseBuilder(context, CountryDB::class.java, "CountryDB").build()
    }
}