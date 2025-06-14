package com.target.targetcasestudy.deals.domain

import com.target.targetcasestudy.core.network.ApiResult
import retrofit2.http.GET
import retrofit2.http.Path

interface DealsRetrofitApiInterface {
    @GET("deals")
    suspend fun getDeals(): ApiResult<Deals>

    @GET("deals/{id}")
    suspend fun getDealItem(
        @Path("id") id: Int
    ): ApiResult<DealItem>
}