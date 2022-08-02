package com.example.nycschools.ui.main.list.model

import com.example.nycschools.network.model.SchoolsList

/**
 * UI models to represent to the User.
 */
class SchoolsDataList(val schools: List<SchoolUiModel>) {
    companion object {
        fun build(list: SchoolsList): SchoolsDataList {
            val schools = ArrayList<SchoolUiModel>()
            list.forEach {
                schools.add(
                    SchoolUiModel(
                        it.school_name,
                        it.dbn,
                        "${it.city} , ${it.zip}",
                        it.phone_number
                    )
                )
            }
            return SchoolsDataList(schools)
        }
    }
}