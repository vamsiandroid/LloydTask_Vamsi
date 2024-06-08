package com.vamsi.lloydtask.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vamsi.lloydtask.model.Country

@Database(entities = [Country::class], version = 1)
abstract class CountryDB : RoomDatabase() {

    abstract fun getCountryDAO() : CountryDAO

}