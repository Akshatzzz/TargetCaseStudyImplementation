package com.target.targetcasestudy.presentation.uimodels

import androidx.compose.runtime.Immutable

@Immutable
data class DealsListState(
    val isLoading: Boolean = false,
    val dealUiItems: List<DealItemUI> = emptyList(),
    val selectedDeal: DealItemUI? = null
)