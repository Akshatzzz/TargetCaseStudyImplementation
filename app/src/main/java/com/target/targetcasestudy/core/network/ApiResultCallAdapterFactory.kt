package com.target.targetcasestudy.core.network

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ApiResultCallAdapterFactory private constructor() : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        // Check if returnType is Call<*>
        if (getRawType(returnType) != Call::class.java) return null
        if (returnType !is ParameterizedType) return null

        // Check if the type inside Call is ApiResult<*>
        val callType = getParameterUpperBound(0, returnType)
        if (getRawType(callType) != ApiResult::class.java) return null
        if (callType !is ParameterizedType) return null

        // Get the type inside ApiResult<T>
        val resultType = getParameterUpperBound(0, callType)

        return ApiResultCallAdapter<Any>(resultType)
    }

    companion object {
        fun create(): ApiResultCallAdapterFactory = ApiResultCallAdapterFactory()
    }
}
