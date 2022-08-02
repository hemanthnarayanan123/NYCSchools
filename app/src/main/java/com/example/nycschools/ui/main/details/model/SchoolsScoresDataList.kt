package com.example.nycschools.ui.main.details.model

import com.example.nycschools.network.model.SchoolScoresList

/**
 * UI models to represent to the User.
 */
class SchoolsScoresDataList(val school: SchoolScoresUiModel?) {
    companion object {
        /**
         * Finds the one school that we are interested in from the entire collection
         * and then returns the UI model for the same.
         */
        fun build(list: SchoolScoresList, schoolId: String): SchoolsScoresDataList {
            list.firstOrNull { it.dbn == schoolId }?.let {
                return SchoolsScoresDataList(
                    SchoolScoresUiModel(
                        name = it.school_name,
                        id = it.dbn,
                        satReading = it.sat_critical_reading_avg_score,
                        satMath = it.sat_math_avg_score,
                        satWriting = it.sat_writing_avg_score,
                        totalAppeared = it.num_of_sat_test_takers
                    )
                )
            }
            return SchoolsScoresDataList(null)
        }
    }
}