package com.corevalue.tutorial.data.api

sealed class ApiResult<T>(
    val data: T? = null,
    val message: String? = null,
    val statusCode: Int? = 0
) {
    class Success<T>(
        data: T,
        val isFromCache: Boolean = false
    ) : ApiResult<T>(data)

    class Error<T>(message: String, statusCode: Int = 0, data: T? = null) :
        ApiResult<T>(data, message, statusCode)

    class Loading<T>(data: T? = null) : ApiResult<T>(data)
}