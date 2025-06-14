package com.target.targetcasestudy.deals.presentation.deallist

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.target.targetcasestudy.deals.domain.prevDealItem
import com.target.targetcasestudy.deals.presentation.deallist.components.DealItemComposable
import com.target.targetcasestudy.deals.presentation.uimodels.DealsListState
import com.target.targetcasestudy.deals.presentation.uimodels.toDealItemUI
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.shareIn

@Composable
fun DealsListScreen(
    modifier: Modifier = Modifier,
    dealsListState: DealsListState,
    dealsListAction: (DealsListAction) -> Unit
) {
    if (dealsListState.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(dealsListState.dealUiItems) {
                DealItemComposable(dealItem = it) { dealUi ->
                    dealsListAction(DealsListAction.OnDealClick(dealUi))
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    thickness = 1.dp,
                )
            }
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
@SuppressLint("FlowOperatorInvokedInComposition")
@Preview
@Composable
private fun DealsListPreview() {
    DealsListScreen(dealsListState = DealsListState(dealUiItems = List(100) {
        prevDealItem.toDealItemUI()
    })) {}
}