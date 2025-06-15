package com.target.targetcasestudy.deals.presentation.detailscreen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.target.targetcasestudy.core.components.HeaderComposable
import com.target.targetcasestudy.core.components.HeaderComposablePayload
import com.target.targetcasestudy.deals.presentation.detailscreen.components.AddToCardButtonComposable
import com.target.targetcasestudy.deals.presentation.detailscreen.components.ProductDetailComposable
import com.target.targetcasestudy.deals.presentation.uimodels.DealDetailState

@Composable
fun DealDetailScreen(
    modifier: Modifier = Modifier, dealDetailState: DealDetailState, onBackClick: () -> Unit,onAddToCardClicked: (id: Int) -> Unit
) {
    if (dealDetailState.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (dealDetailState.dealDetailElement != null) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            HeaderComposable(
                headerComposablePayload = HeaderComposablePayload("Details", true)
            ) {
                onBackClick()
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(ScrollState(0))
            ) {
                ProductDetailComposable(
                    dealItemUI = dealDetailState.dealDetailElement
                )
            }
            Divider(Modifier.fillMaxWidth().shadow(elevation = 2.dp))
            AddToCardButtonComposable {
                onAddToCardClicked.invoke(dealDetailState.dealDetailElement.id)
            }
        }
    }
}
