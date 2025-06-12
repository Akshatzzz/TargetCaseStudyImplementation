package com.target.targetcasestudy.deals.data

import com.target.targetcasestudy.core.network.ApiResult
import com.target.targetcasestudy.deals.domain.Deals
import com.target.targetcasestudy.deals.domain.DealsDataSource
import com.target.targetcasestudy.deals.domain.DealsRetrofitApiInterface

class RemoteDealsDataSource(private val dealsRetrofitApiInterface: DealsRetrofitApiInterface) :
    DealsDataSource {
    override suspend fun getDealsList(): ApiResult<Deals> {
        return dealsRetrofitApiInterface.getDeals()
    }
}