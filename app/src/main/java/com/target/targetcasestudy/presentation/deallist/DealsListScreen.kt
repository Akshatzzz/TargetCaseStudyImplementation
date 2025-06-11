package com.target.targetcasestudy.presentation.deallist

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.target.targetcasestudy.domain.prevDealItem
import com.target.targetcasestudy.presentation.deallist.components.DealItemComposable
import com.target.targetcasestudy.presentation.uimodels.DealsListState
import com.target.targetcasestudy.presentation.uimodels.toDealItemUI
import com.target.targetcasestudy.theme.primaryRed

@Composable
fun DealsListScreen(modifier: Modifier = Modifier, dealsListState: DealsListState) {
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
                DealItemComposable(dealItem = it)
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                )
            }
        }
    }
}

@Preview
@Composable
private fun DealsListPreview() {
    DealsListScreen(dealsListState = DealsListState(dealUiItems = List(100) {
        prevDealItem.toDealItemUI()
    }))
}