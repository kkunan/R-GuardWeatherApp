package com.kkunan.core.data.exceptions

sealed class ServerException: Exception() {
    class BadResponse(val errorMessage: String): ServerException()
}