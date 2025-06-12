package com.target.targetcasestudy.core.network

import retrofit2.HttpException
import retrofit2.Response


sealed class ApiResult<T : Any> {
    class Success<T : Any>(val data: T) : ApiResult<T>()
    class Error<T : Any>(val code: Int, val message: String?) : ApiResult<T>()
    class Exception<T : Any>(val e: Throwable) : ApiResult<T>()
}

fun <T : Any> handleApi(
    execute: () -> Response<T>
): ApiResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            ApiResult.Success(body)
        } else {
            ApiResult.Error(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        ApiResult.Error(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        ApiResult.Exception(e)
    }
}

suspend fun <T : Any> ApiResult<T>.onSuccess(
    executable: suspend (T) -> Unit
): ApiResult<T> = apply {
    if (this is ApiResult.Success<T>) {
        executable(data)
    }
}

suspend fun <T : Any> ApiResult<T>.onError(
    executable: suspend (code: Int, message: String?) -> Unit
): ApiResult<T> = apply {
    if (this is ApiResult.Error<T>) {
        executable(code, message)
    }
}

suspend fun <T : Any> ApiResult<T>.onException(
    executable: suspend (e: Throwable) -> Unit
): ApiResult<T> = apply {
    if (this is ApiResult.Exception<T>) {
        executable(e)
    }
}