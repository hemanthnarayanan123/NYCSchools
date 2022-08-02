package com.example.nycschools.ui.main.details.model

import com.example.nycschools.ui.main.model.IDiffCompare

/**
 * UI model to represent to the User.
 */
class SchoolScoresUiModel(
    val name: String,
    val id: String,
    val satReading: String,
    val satMath: String,
    val satWriting: String,
    val totalAppeared: String,
) : IDiffCompare {
    override fun areItemsSame(other: IDiffCompare?): Boolean {
        return other is SchoolScoresUiModel && id == other.id
    }

    override fun areContentsSame(other: IDiffCompare?): Boolean {
        other as SchoolScoresUiModel
        return name == other.name
                && satMath == other.satMath
                && satReading == other.satReading
                && satWriting == other.satWriting
                && totalAppeared == other.totalAppeared
    }
}