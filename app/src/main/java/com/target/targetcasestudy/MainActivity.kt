package com.target.targetcasestudy

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.target.targetcasestudy.core.components.HeaderComposable
import com.target.targetcasestudy.core.components.HeaderComposablePayload
import com.target.targetcasestudy.core.navigation.Screen
import com.target.targetcasestudy.deals.presentation.deallist.DealsListAction
import com.target.targetcasestudy.deals.presentation.deallist.DealsListScreen
import com.target.targetcasestudy.deals.presentation.deallist.DealsListViewModel
import com.target.targetcasestudy.deals.presentation.detailscreen.DealDetailScreen
import com.target.targetcasestudy.deals.presentation.uimodels.DealDetailState
import com.target.targetcasestudy.deals.presentation.uimodels.DealsListState
import com.target.targetcasestudy.theme.CaseStudyTheme
import com.target.targetcasestudy.theme.primaryRed
import kotlinx.coroutines.flow.SharedFlow
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideSystemBars()

        setContent {
            CaseStudyTheme {
                Scaffold(contentWindowInsets = WindowInsets.safeDrawing) { innerPadding ->
                    val viewModel: DealsListViewModel = koinViewModel()
                    val navController = rememberNavController()
                    val state = viewModel.dealsListState.collectAsStateWithLifecycle()
                    val detailState = viewModel.dealDetailState.collectAsStateWithLifecycle()
                    App(innerPadding, viewModel, navController, state, detailState)
                    StatusBarProtection()

                    ToastCollector(viewModel.toastEvent)
                }
            }
        }
    }

    @Composable
    private fun App(
        innerPadding: PaddingValues,
        viewModel: DealsListViewModel,
        navController: NavHostController,
        state: State<DealsListState>,
        detailState: State<DealDetailState>
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.DealsList.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Screen.DealsList.route) {
                Column {
                    HeaderComposable(
                        headerComposablePayload = HeaderComposablePayload(
                            "List", false
                        )
                    )
                    DealsListScreen(
                        dealsListState = state.value
                    ) { action ->
                        viewModel.onAction(action)
                        navController.navigate(Screen.DealDetails.route)
                    }
                }
            }

            composable(route = Screen.DealDetails.route) {
                DealDetailScreen(
                    dealDetailState = detailState.value,
                    onBackClick = { navController.popBackStack() }
                ) {
                    viewModel.onAction(DealsListAction.onAddToCartClicked(it))
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
    color: Color = primaryRed,
    heightProvider: () -> Float = calculateGradientHeight(),
) {

    Canvas(Modifier.fillMaxSize()) {
        val calculatedHeight = heightProvider()
        val gradient = Brush.verticalGradient(
            colors = listOf(
                color.copy(alpha = 1f), color.copy(alpha = 1f), color
            ), startY = 0f, endY = calculatedHeight
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

@Composable
fun ToastCollector(toastFlow: SharedFlow<String>) {
    val context = LocalContext.current

    LaunchedEffect(key1 = toastFlow) {
        toastFlow.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}