package com.example.nycschools.ui.main.list.model

import com.example.nycschools.ui.main.model.IDiffCompare

/**
 * UI model to represent to the User.
 */
class SchoolUiModel(
    val name: String,
    val id: String,
    val description: String,
    val phoneNumber: String
) : IDiffCompare {
    override fun areItemsSame(other: IDiffCompare?): Boolean {
        return other is SchoolUiModel && id == other.id
    }

    override fun areContentsSame(other: IDiffCompare?): Boolean {
        other as SchoolUiModel
        return name == other.name && description == other.description && phoneNumber == other.phoneNumber
    }
}