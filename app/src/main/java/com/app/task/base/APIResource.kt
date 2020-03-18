package com.app.task.base

data class APIResource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): APIResource<T> {
            return APIResource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(msg: String, data: T?): APIResource<T> {
            return APIResource(
                Status.ERROR,
                data,
                msg
            )
        }

        fun <T> loading(data: T?): APIResource<T> {
            return APIResource(
                Status.LOADING,
                data,
                null
            )
        }
    }
}