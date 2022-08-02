package com.example.nycschools.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nycschools.R
import com.example.nycschools.ui.main.list.SchoolsListFragment

/**
 * The container activity that holds the main schoolds fragments and other child
 * fragments.
 */
class SchoolsListActivity : AppCompatActivity() {

    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, SchoolsListActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SchoolsListFragment.newInstance())
                .commitNow()
        }
    }
}