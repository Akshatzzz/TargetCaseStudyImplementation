package com.target.targetcasestudy

import com.target.targetcasestudy.core.network.ApiResult
import com.target.targetcasestudy.core.utils.ADD_TO_CART_CLICKED
import com.target.targetcasestudy.core.utils.SOMETHING_WENT_WRONG
import com.target.targetcasestudy.deals.domain.Deals
import com.target.targetcasestudy.deals.domain.DealsDataSource
import com.target.targetcasestudy.deals.domain.prevDealItem
import com.target.targetcasestudy.deals.presentation.deallist.DealsListAction
import com.target.targetcasestudy.deals.presentation.deallist.DealsListViewModel
import com.target.targetcasestudy.deals.presentation.uimodels.DealItemUI
import com.target.targetcasestudy.deals.presentation.uimodels.toDealItemUI
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

import org.junit.Assert.*

@OptIn(ExperimentalCoroutinesApi::class)
class DealsListViewModelTest {

    private lateinit var viewModel: DealsListViewModel
    private lateinit var mockDataSource: DealsDataSource
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        mockDataSource = mock()
        viewModel = DealsListViewModel(mockDataSource)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadDeals success updates deal list state`() = runTest {
        val fakeDeals = Deals(products = listOf(prevDealItem))
        whenever(mockDataSource.getDealsList()).thenReturn(ApiResult.Success(fakeDeals))

        val stateHandled = CompletableDeferred<Unit>()
        launch {
            // dropping first two emissions because, first collection will be the default value and second one will be loading state
            viewModel.dealsListState.drop(1).collect {
                assertEquals(false, it.isLoading)
                assertEquals(listOf<DealItemUI>(prevDealItem.toDealItemUI()), it.dealUiItems)
                stateHandled.complete(Unit)
                cancel()
            }
        }

        stateHandled.await()
    }

    @Test
    fun `loadDeals fails`() = runTest {
        whenever(mockDataSource.getDealsList())
            .thenReturn(ApiResult.Exception(RuntimeException("Network down")))

        val stateHandled = CompletableDeferred<Unit>()
        val toastDeferred = async { viewModel.toastEvent.first() }

        launch {
            // dropping first two emissions because, first collection will be the default value and second one will be loading state
            viewModel.dealsListState.drop(2).collect {
                assertEquals(false, it.isLoading)
                assertEquals(emptyList<DealItemUI>(), it.dealUiItems)
                stateHandled.complete(Unit)
                cancel()
            }
        }

        stateHandled.await()
        val toast = toastDeferred.await()
        assertTrue(toast.contains(SOMETHING_WENT_WRONG))
    }



    @Test
    fun `onAddToCartClicked emits correct toast`() = runTest {
        // collecting the events in advance, before any action
        val toast = async{ viewModel.toastEvent.first() }

        viewModel.onAction(DealsListAction.onAddToCartClicked(42))

        assertEquals("$ADD_TO_CART_CLICKED 42", toast.await())
    }

    @Test
    fun `loadDealsForId success updates dealDetailState`() = runTest {
        whenever(mockDataSource.getDealForId(0)).thenReturn(ApiResult.Success(prevDealItem))

        viewModel.onAction(DealsListAction.OnDealClick(prevDealItem.toDealItemUI()))
        advanceUntilIdle()

        val detailState = viewModel.dealDetailState.value
        assertFalse(detailState.isLoading)
        assertEquals(prevDealItem.title, detailState.dealDetailElement?.title)
    }

    @Test
    fun `loadDealsForId failure emits toast`() = runTest {
        // Given
        whenever(mockDataSource.getDealForId(0)).thenReturn(ApiResult.Error(404, "Not Found"))
        val toastDeferred = async {
            viewModel.toastEvent.first()
        }

        // When
        viewModel.onAction(DealsListAction.OnDealClick(prevDealItem.toDealItemUI()))

        // Then
        val toast = toastDeferred.await()
        assertTrue(toast.contains(SOMETHING_WENT_WRONG))
    }

}
