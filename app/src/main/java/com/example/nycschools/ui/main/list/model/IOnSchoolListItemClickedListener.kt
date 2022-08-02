package com.example.nycschools.ui.main.list.model

interface IOnSchoolListItemClickedListener {
    fun onSchoolSelected(id: String)
    fun onCallSchoolClicked(number: String)
}