package com.target.targetcasestudy.deals.presentation.deallist

import com.target.targetcasestudy.deals.presentation.uimodels.DealItemUI

sealed interface DealsListAction {
    data class OnDealClick(val dealItemUI: DealItemUI): DealsListAction

    data class onAddToCartClicked(val itemId: Int): DealsListAction
}