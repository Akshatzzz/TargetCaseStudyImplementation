package com.target.targetcasestudy.core.network

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiResultCall<T : Any>(
    private val proxy: Call<T>
) : Call<ApiResult<T>> {
    override fun execute(): Response<ApiResult<T>> = throw NotImplementedError()
    override fun enqueue(callback: Callback<ApiResult<T>>) {
        proxy.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val result: ApiResult<T> = handleApi { response }
                callback.onResponse(this@ApiResultCall, Response.success(result))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val result = ApiResult.Exception<T>(t)
                callback.onResponse(this@ApiResultCall, Response.success(result))
            }

        })
    }

    override fun clone(): Call<ApiResult<T>> = ApiResultCall<T>(proxy.clone())
    override fun request(): Request = proxy.request()
    override fun timeout(): Timeout = proxy.timeout()
    override fun isExecuted(): Boolean = proxy.isExecuted
    override fun isCanceled(): Boolean = proxy.isCanceled
    override fun cancel() {
        proxy.cancel()
    }
}