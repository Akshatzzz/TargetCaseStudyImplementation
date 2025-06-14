package com.target.targetcasestudy.deals.domain

import com.target.targetcasestudy.core.network.ApiResult

interface DealsDataSource {
    suspend fun getDealsList(): ApiResult<Deals>
    suspend fun getDealForId(dealId: Int): ApiResult<DealItem>
}