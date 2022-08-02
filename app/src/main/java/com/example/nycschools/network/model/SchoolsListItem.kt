package com.example.nycschools.network.model

/**
 * Server model returning the information about each school.
 */
data class SchoolsListItem(
    val school_name: String,
    val city: String,
    val dbn: String,
    val phone_number: String,
    val zip: String
)