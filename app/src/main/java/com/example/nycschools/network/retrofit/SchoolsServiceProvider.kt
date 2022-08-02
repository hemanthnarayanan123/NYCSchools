package com.example.nycschools.network.retrofit

import android.content.Context
import com.example.nycschools.network.Api
import com.example.nycschools.network.OkHttpProvider
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SchoolsServiceProvider(appContext: Context) {

    val schoolsService: SchoolsService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Api.API_BASE_URL)
            .client(OkHttpProvider.getClient(appContext).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        schoolsService = retrofit.create(SchoolsService::class.java)
    }
}