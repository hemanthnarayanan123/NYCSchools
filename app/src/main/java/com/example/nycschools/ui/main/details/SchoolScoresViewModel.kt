package com.example.nycschools.ui.main.details

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschools.network.NetworkServiceProvider
import com.example.nycschools.ui.main.details.model.SchoolsScoresDataList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SchoolScoresViewModel : ViewModel() {
    private val networkProvider = NetworkServiceProvider()

    val resultsLiveData: LiveData<SchoolsScoresDataList> =
        Transformations.map(networkProvider.getSchoolsScoresListLiveData()) {
            it
        }

    val resultsErrorLiveData: LiveData<String?> =
        Transformations.map(networkProvider.getSchoolsScoresListErrorLiveData()) {
            it
        }

    fun fetchSchoolSATScores(applicationContext: Context, schoolId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            networkProvider.getSchoolsScoresList(applicationContext, schoolId)
        }
    }
}