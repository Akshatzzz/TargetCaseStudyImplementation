package com.target.targetcasestudy.deals.presentation.detailscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.target.targetcasestudy.core.components.HeaderComposable
import com.target.targetcasestudy.core.components.HeaderComposablePayload
import com.target.targetcasestudy.deals.presentation.detailscreen.components.AddToCardButtonComposable
import com.target.targetcasestudy.deals.presentation.detailscreen.components.ProductDetailComposable
import com.target.targetcasestudy.deals.presentation.uimodels.DealItemUI

@Composable
fun DealDetailScreen(modifier: Modifier = Modifier,dealItemUI: DealItemUI) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HeaderComposable(headerComposablePayload = HeaderComposablePayload("Details",true))

        ProductDetailComposable(modifier = Modifier.weight(1f),dealItemUI = dealItemUI)

        Spacer(modifier = Modifier.weight(1f))
        AddToCardButtonComposable() {
            TODO()
        }
    }
}