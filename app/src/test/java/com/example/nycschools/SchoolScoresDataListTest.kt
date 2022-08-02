package com.example.nycschools

import com.example.nycschools.network.model.SchoolScoresList
import com.example.nycschools.network.model.SchoolScoresListItem
import com.example.nycschools.ui.main.details.model.SchoolsScoresDataList
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SchoolScoresDataListTest {
    @Test
    fun dataListBuildHappensCorrectly() {
        val school1 = SchoolScoresListItem("1", "32", "1", "123", "555", "School 1")
        val school2 = SchoolScoresListItem("2", "45", "2", "123", "555", "School 2")
        val dataList = SchoolScoresList()
        dataList.add(school1)
        dataList.add(school2)
        val score = SchoolsScoresDataList.build(dataList, "2")
        assertEquals("2", score.school?.id)
        assertEquals("45", score.school?.totalAppeared)
    }
}