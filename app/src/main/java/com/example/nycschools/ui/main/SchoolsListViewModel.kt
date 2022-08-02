package com.example.nycschools.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschools.network.NetworkServiceProvider
import com.example.nycschools.ui.main.list.model.SchoolsDataList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SchoolsListViewModel : ViewModel() {
    private val networkProvider = NetworkServiceProvider()

    val resultsLiveData: LiveData<SchoolsDataList> =
        Transformations.map(networkProvider.getSchoolsListLiveData()) {
            it
        }
    val resultsErrorLiveData: LiveData<String?> =
        Transformations.map(networkProvider.getSchoolsListErrorLiveData()) {
            it
        }

    fun fetchSchoolsList(applicationContext: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            networkProvider.getSchoolsList(applicationContext)
        }
    }
}