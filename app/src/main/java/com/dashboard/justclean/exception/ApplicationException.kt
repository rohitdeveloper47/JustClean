package com.dashboard.justclean.exception

class ApplicationException(override val message: String) : Throwable(message) {
    var type: Type? = null

    enum class Type {
        NO_INTERNET, NO_DATA, VALIDATION
    }
}