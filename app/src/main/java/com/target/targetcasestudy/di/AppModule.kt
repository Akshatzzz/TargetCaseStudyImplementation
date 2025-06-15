package com.target.targetcasestudy.di

import com.target.targetcasestudy.BuildConfig
import com.target.targetcasestudy.core.network.ApiResultCallAdapterFactory
import com.target.targetcasestudy.deals.data.RemoteDealsDataSource
import com.target.targetcasestudy.deals.domain.DealsDataSource
import com.target.targetcasestudy.deals.domain.DealsRetrofitApiInterface
import com.target.targetcasestudy.deals.presentation.deallist.DealsListViewModel
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

val appModule = module {
    single {
        OkHttpClient()
    }
    single {
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(get())
            .addCallAdapterFactory(ApiResultCallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create()).build()
            .create<DealsRetrofitApiInterface>()
    }
    singleOf(::RemoteDealsDataSource).bind<DealsDataSource>()
    viewModelOf(::DealsListViewModel)
}