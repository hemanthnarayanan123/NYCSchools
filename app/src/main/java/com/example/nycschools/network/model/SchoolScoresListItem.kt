package com.example.nycschools.network.model

/**
 * Server model storing each school score information.
 */
data class SchoolScoresListItem(
    val dbn: String,
    val num_of_sat_test_takers: String,
    val sat_critical_reading_avg_score: String,
    val sat_math_avg_score: String,
    val sat_writing_avg_score: String,
    val school_name: String
)