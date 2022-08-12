package com.kkunan.core.data.exceptions

sealed class ServerException {
    class NoResponse: ServerException()
    class BadResponse(val errorMessage: String): ServerException()
}