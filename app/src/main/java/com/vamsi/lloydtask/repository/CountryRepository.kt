package com.vamsi.lloydtask.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vamsi.lloydtask.db.CountryDB
import com.vamsi.lloydtask.model.Country
import com.vamsi.lloydtask.retrofit.CountriesApi
import javax.inject.Inject



class CountryRepository @Inject constructor(
    private val countriesApi: CountriesApi,
    private val countryDB: CountryDB
) {

    private val _countries = MutableLiveData<List<Country>>() // private member only
    val countries: LiveData<List<Country>> // public and read only.
        get() = _countries

    // suspend function to call the api
    suspend fun getCountries() {
        val result = countriesApi.getCountries()
        if (result.body() != null) {
            result.body()?.let { body ->
                countryDB.getCountryDAO().addCountries(body)
                _countries.postValue(body)
            }


        }

    }

}

