package com.vamsi.lloydtask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vamsi.lloydtask.model.Country
import com.vamsi.lloydtask.repository.CountryRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val repository: CountryRepository,
) : ViewModel() {

    val countriesLiveData: LiveData<List<Country>>
        get() = repository.countries

    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    fun refresh() {
        loading.value = true
        fetchCountries()
    }

    private fun fetchCountries() {
        viewModelScope.launch {
            repository.getCountries()
            loading.value = false
        }
    }

}

