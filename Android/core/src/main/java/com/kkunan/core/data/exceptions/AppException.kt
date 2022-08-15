package com.kkunan.core.data.exceptions

sealed class AppException: Exception(){
    class NoInternet: AppException()
    class BadRequest(val errorMessage: String): AppException()
}
