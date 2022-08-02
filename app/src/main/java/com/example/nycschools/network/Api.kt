package com.example.nycschools.network

import com.example.nycschools.BuildConfig

object Api {
    // TODO: Eventually doing separate end points if needed for debug and prod.
    val API_BASE_URL = if (BuildConfig.DEBUG) "https://data.cityofnewyork.us" else "https://data.cityofnewyork.us"
}