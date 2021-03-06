package com.dashboard.justclean.data.Model

data class ResponseBody<out T>(val status: Status, val data: T?, val message: String?){

    companion object {

        fun <T> success(data: T?): ResponseBody<T>? {
            return ResponseBody(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): ResponseBody<T> {
            return ResponseBody(Status.ERROR, data, msg)
        }
    }

    enum class Status{

        SUCCESS,
        ERROR
    }
}