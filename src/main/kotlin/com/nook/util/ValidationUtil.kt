package com.nook.util

import com.nook.global.ErrorCode
import com.nook.global.ServiceException
import io.ktor.http.*

fun validate(value: Boolean, errorCode: ErrorCode): Unit {
    if (!value) {
        throw ServiceException(HttpStatusCode.BadRequest, errorCode)
    }
}