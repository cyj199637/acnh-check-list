package com.nook.global

import io.ktor.http.*

class ServiceException(val statusCode: HttpStatusCode, val errorCode: ErrorCode)
    : RuntimeException(errorCode.message) {
}