package com.example.nycschools.network

import android.content.Context
import android.webkit.WebSettings
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File

object OkHttpProvider {
    private const val HTTP_CACHE_DIR = "http"

    fun getClient(app: Context): OkHttpClient.Builder {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
        var cache: Cache? = null
        val appCacheDir = app.cacheDir

        // guard for possible null cache dir
        if (appCacheDir != null) {
            cache = Cache(File(appCacheDir, HTTP_CACHE_DIR), 10 * 1024 * 1024)
        }
        builder.addNetworkInterceptor { chain ->
            val requestBuilder: Request.Builder = chain.request().newBuilder()
            val userAgent = getUserAgent(app)
            requestBuilder.header("User-Agent", userAgent)
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
        builder.cache(cache)
        return builder
    }

    private fun getUserAgent(c: Context): String {
        // Strip any potential non-ASCII characters from the user agent string
        return WebSettings.getDefaultUserAgent(c).replace("[^\\p{ASCII}]".toRegex(), "")
    }
}