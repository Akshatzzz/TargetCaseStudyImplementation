package com.target.targetcasestudy.deals.presentation.deallist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.core.network.onFailure
import com.target.targetcasestudy.core.network.onSuccess
import com.target.targetcasestudy.core.utils.SOMETHING_WENT_WRONG
import com.target.targetcasestudy.deals.domain.DealsDataSource
import com.target.targetcasestudy.deals.presentation.uimodels.DealDetailState
import com.target.targetcasestudy.deals.presentation.uimodels.DealsListState
import com.target.targetcasestudy.deals.presentation.uimodels.toDealItemUI
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DealsListViewModel(
    private val dealsDataSource: DealsDataSource
) : ViewModel() {

    private val _dealsListState = MutableStateFlow<DealsListState>(DealsListState())
    val dealsListState = _dealsListState.onStart { loadDeals() }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = DealsListState()
    )

    private val _dealDetailState = MutableStateFlow<DealDetailState>(DealDetailState())
    val dealDetailState = _dealDetailState.asStateFlow()

    private val _dealsListEvents = MutableSharedFlow<DealsListEvent>()
    val dealsListEvent = _dealsListEvents.asSharedFlow()

    private fun loadDeals() {
        viewModelScope.launch {
            _dealsListState.update {
                it.copy(isLoading = true)
            }

            dealsDataSource.getDealsList().onSuccess { deals ->
                _dealsListState.update {
                    it.copy(
                        isLoading = false, dealUiItems = deals.products.map { it.toDealItemUI() })
                }
            }.onFailure {
                viewModelScope.launch {
                    _dealsListState.update {
                        it.copy(isLoading = false, dealUiItems = emptyList())
                    }
                    _dealsListEvents.emit(DealsListEvent.Error(SOMETHING_WENT_WRONG))
                }
            }
        }
    }

    fun onAction(action: DealsListAction) {
        when (action) {
            is DealsListAction.OnDealClick -> {
                loadDealsForId(action.dealItemUI.id)
            }
        }
    }

    private fun loadDealsForId(dealId: Int) {
        viewModelScope.launch {
            _dealDetailState.update {
                it.copy(isLoading = true)
            }

            dealsDataSource.getDealForId(dealId).onSuccess { deal ->
                _dealDetailState.update {
                    it.copy(
                        isLoading = false, dealDetailElement = deal.toDealItemUI()
                    )
                }
            }.onFailure {
                viewModelScope.launch {
                    _dealDetailState.update {
                        it.copy(isLoading = false, dealDetailElement = null)
                    }

                }
            }
        }
    }
}