package com.target.targetcasestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.target.targetcasestudy.domain.prevDealItem
import com.target.targetcasestudy.presentation.deallist.DealsListScreen
import com.target.targetcasestudy.presentation.uimodels.DealsListState
import com.target.targetcasestudy.presentation.uimodels.toDealItemUI
import com.target.targetcasestudy.theme.CaseStudyTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideSystemBars()
        setContent {
            CaseStudyTheme {
                Scaffold(
                    contentWindowInsets = WindowInsets.safeDrawing,
                ) { innerPadding ->
                    DealsListScreen(
                        modifier = Modifier
                            .padding(innerPadding),
                        dealsListState = DealsListState(dealUiItems = List(100) {
                            prevDealItem.toDealItemUI()
                        })
                    )

                    StatusBarProtection()
                }
            }
        }
    }

    private fun hideSystemBars() {
        val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())
    }
}

@Composable
private fun StatusBarProtection(
    color: Color = Color.Red,
    heightProvider: () -> Float = calculateGradientHeight(),
) {

    Canvas(Modifier.fillMaxSize()) {
        val calculatedHeight = heightProvider()
        val gradient = Brush.verticalGradient(
            colors = listOf(
                color.copy(alpha = 1f),
                color.copy(alpha = 1f),
                color
            ),
            startY = 0f,
            endY = calculatedHeight
        )
        drawRect(
            brush = gradient,
            size = Size(size.width, calculatedHeight),
        )
    }
}

@Composable
fun calculateGradientHeight(): () -> Float {
    val statusBars = WindowInsets.statusBars
    val density = LocalDensity.current
    return { statusBars.getTop(density).times(1f) }
}