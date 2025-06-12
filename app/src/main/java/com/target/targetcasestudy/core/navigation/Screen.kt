package com.target.targetcasestudy.core.navigation

sealed class Screen(val route: String) {
    object DealsList : Screen("deals_list")
    object DealDetails : Screen("deal_details")
}