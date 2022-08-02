package com.example.nycschools.network

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nycschools.network.retrofit.SchoolsServiceProvider
import com.example.nycschools.ui.main.details.model.SchoolsScoresDataList
import com.example.nycschools.ui.main.list.model.SchoolsDataList

@Suppress("BlockingMethodInNonBlockingContext")
class NetworkServiceProvider : ISchoolsNetworkProvider {
    private val schoolsListLiveData = MutableLiveData<SchoolsDataList>()
    private val schoolsListErrorLiveData = MutableLiveData<String?>()

    override fun getSchoolsListLiveData(): LiveData<SchoolsDataList> {
        return schoolsListLiveData
    }

    override fun getSchoolsListErrorLiveData(): LiveData<String?> {
        return schoolsListErrorLiveData
    }

    override suspend fun getSchoolsList(
        applicationContext: Context,
    ) {
        // Clear any existing errors.
        schoolsListErrorLiveData.postValue(null)

        val response =
            SchoolsServiceProvider(applicationContext).schoolsService.getSchoolsList().execute()
        if (response.isSuccessful && response.body() != null) {
            schoolsListLiveData.postValue(SchoolsDataList.build(response.body()!!))
        } else {
            schoolsListErrorLiveData.postValue("Empty response?")
        }
    }

    private val schoolsScoresListLiveData = MutableLiveData<SchoolsScoresDataList>()
    private val schoolsScoresListErrorLiveData = MutableLiveData<String?>()

    override fun getSchoolsScoresListLiveData(): LiveData<SchoolsScoresDataList> {
        return schoolsScoresListLiveData
    }

    override fun getSchoolsScoresListErrorLiveData(): LiveData<String?> {
        return schoolsScoresListErrorLiveData
    }

    override suspend fun getSchoolsScoresList(
        applicationContext: Context,
        schoolId: String,
    ) {
        // Clear any existing errors.
        schoolsScoresListErrorLiveData.postValue(null)

        val response =
            SchoolsServiceProvider(applicationContext).schoolsService.getSchoolsScoresList()
                .execute()
        if (response.isSuccessful && response.body() != null) {
            schoolsScoresListLiveData.postValue(
                SchoolsScoresDataList.build(
                    response.body()!!,
                    schoolId
                )
            )
        } else {
            schoolsScoresListErrorLiveData.postValue("Empty response?")
        }
    }
}