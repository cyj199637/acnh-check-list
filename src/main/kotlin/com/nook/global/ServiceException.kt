package com.nook.global

import io.ktor.http.*

open class ServiceException(val statusCode: HttpStatusCode, val errorCode: ErrorCode)
    : RuntimeException(errorCode.message) {
}