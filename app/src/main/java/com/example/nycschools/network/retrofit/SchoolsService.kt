package com.example.nycschools.network.retrofit

import com.example.nycschools.network.model.SchoolScoresList
import com.example.nycschools.network.model.SchoolsList
import retrofit2.Call
import retrofit2.http.GET

interface SchoolsService {
    @GET("/resource/s3k6-pzi2.json")
    fun getSchoolsList(): Call<SchoolsList>

    @GET("/resource/f9bf-2cp4.json")
    fun getSchoolsScoresList(): Call<SchoolScoresList>
}