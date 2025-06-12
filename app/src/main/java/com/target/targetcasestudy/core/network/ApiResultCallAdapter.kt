package com.target.targetcasestudy.core.network

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ApiResultCallAdapter<T: Any>(
    private val resultType: Type
) : CallAdapter<T, Call<ApiResult<T>>> {

    override fun responseType(): Type = resultType

    override fun adapt(call: Call<T>): Call<ApiResult<T>> {
        return ApiResultCall(call)
    }
}