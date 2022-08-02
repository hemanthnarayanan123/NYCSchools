package com.example.nycschools

import com.example.nycschools.network.model.SchoolsList
import com.example.nycschools.network.model.SchoolsListItem
import com.example.nycschools.ui.main.list.model.SchoolsDataList
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SchoolsDataListTest {
    @Test
    fun dataListBuildHappensCorrectly() {
        val school1 = SchoolsListItem("School 1", "Olathe", "1", "123", "555")
        val school2 = SchoolsListItem("School 2", "Olathe", "2", "123", "555")
        val dataList = SchoolsList()
        dataList.add(school1)
        dataList.add(school2)
        val list = SchoolsDataList.build(dataList)
        assertEquals(2, list.schools.size)
        assertEquals("School 1", list.schools[0].name)
    }
}