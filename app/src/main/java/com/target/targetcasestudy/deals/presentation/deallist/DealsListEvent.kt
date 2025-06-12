package com.target.targetcasestudy.deals.presentation.deallist

sealed interface DealsListEvent {
    data class Error(val errorString: String): DealsListEvent
}