package com.example.nycschools.network

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.nycschools.ui.main.details.model.SchoolsScoresDataList
import com.example.nycschools.ui.main.list.model.SchoolsDataList

/**
 * Marker interface to let us use a different service provider like Retrofit in the future.
 * This makes replacing the service provider like Retrofit easy.
 */
interface ISchoolsNetworkProvider {
    fun getSchoolsListLiveData(): LiveData<SchoolsDataList>
    fun getSchoolsListErrorLiveData(): LiveData<String?>
    suspend fun getSchoolsList(applicationContext: Context)

    fun getSchoolsScoresListLiveData(): LiveData<SchoolsScoresDataList>
    fun getSchoolsScoresListErrorLiveData(): LiveData<String?>
    suspend fun getSchoolsScoresList(applicationContext: Context, schoolId: String)
}