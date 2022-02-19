package com.nook.global

import io.ktor.http.*

class InvalidArgumentException(errorCode: ErrorCode)
    : ServiceException(HttpStatusCode.BadRequest, errorCode)