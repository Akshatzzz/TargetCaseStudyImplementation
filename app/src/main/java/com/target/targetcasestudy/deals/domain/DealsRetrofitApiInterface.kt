package com.target.targetcasestudy.deals.domain

import com.target.targetcasestudy.core.network.ApiResult
import retrofit2.http.GET

interface DealsRetrofitApiInterface {
    @GET("deals")
    suspend fun getDeals(): ApiResult<Deals>
}