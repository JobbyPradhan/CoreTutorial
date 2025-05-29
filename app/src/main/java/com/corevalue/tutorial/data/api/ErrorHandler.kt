package com.corevalue.tutorial.data.api

import android.util.Log
import retrofit2.Response

object ErrorHandler {
    fun apiError(response: Response<*>?): String {
        var message: String
        try {
            message = response?.message().toString()
        } catch (e: Exception) {
            message = "Server Response Error"
        }
        return message
    }

    fun networkError(message: String?): String? {
        return "Fail to connect with server"
    }
}

