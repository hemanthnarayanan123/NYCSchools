package com.example.nycschools.ui.main.model

interface IDiffCompare {
    fun areItemsSame(other: IDiffCompare?): Boolean
    fun areContentsSame(other: IDiffCompare?): Boolean
}