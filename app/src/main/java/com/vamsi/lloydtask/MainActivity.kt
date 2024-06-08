package com.vamsi.lloydtask

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vamsi.lloydtask.model.Country
import com.vamsi.lloydtask.viewmodel.MainViewModel
import com.vamsi.lloydtask.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var countryList: ArrayList<Country>
    private lateinit var countriesAdapter: CountryListAdapter



    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory // Dagger will provide the object to this variable through field injection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as LloydTaskApplication).applicationComponent.inject(this)
     



        mainViewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]

        countryList = arrayListOf()
        mainViewModel.refresh()

        countriesAdapter = CountryListAdapter(arrayListOf(), OnClickListener { item ->
            Toast.makeText(this,"Clicked Country is " + item.countryName, Toast.LENGTH_LONG).show()

        })

        countriesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            mainViewModel.refresh()
        }
        observeViewModel()
    }
    private fun observeViewModel() {

        mainViewModel.countriesLiveData.observe(this) { countries ->
            countries?.let {
                countriesList.visibility = View.VISIBLE
                countryList.addAll(it)
                countriesAdapter.updateCountries(it)
            }
        }
        mainViewModel.countryLoadError.observe(this) { isError ->
            isError?.let { list_error.visibility = if (it) View.VISIBLE else View.GONE }
        }

        mainViewModel.loading.observe(this) { isLoading ->
            isLoading?.let {
                loading_view.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    list_error.visibility = View.GONE
                    countriesList.visibility = View.GONE
                }
            }
        }
    }
}