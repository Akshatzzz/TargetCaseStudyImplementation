package com.target.targetcasestudy.core.utils

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.target.targetcasestudy.BuildConfig
import com.target.targetcasestudy.core.network.ApiResultCallAdapterFactory
import com.target.targetcasestudy.deals.domain.DealsRetrofitApiInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object RetrofitInstance {
    private val client = OkHttpClient()
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // instance for deals retrofit
    val dealsRetrofitInstance by lazy {
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addCallAdapterFactory(ApiResultCallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create()).build()
            .create<DealsRetrofitApiInterface>()
    }
}