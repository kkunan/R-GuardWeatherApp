package com.kkunan.core.data.exceptions

sealed class AppException {
    class NoInternet: AppException()
    class BadRequest(val errorMessage: String): AppException()
}
