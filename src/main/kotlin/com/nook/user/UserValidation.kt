package com.nook.user

import com.nook.global.ErrorCode
import com.nook.global.InvalidArgumentException
import com.nook.user.domain.Hemisphere

private const val MAX_NAME_LENGTH = 20

fun validateName(value: String?) {
    if (value.isNullOrEmpty()) {
        throw InvalidArgumentException(ErrorCode.E1000)
    }

    if (value.length > MAX_NAME_LENGTH) {
        throw InvalidArgumentException(ErrorCode.E1001)
    }
}

fun validateHemisphere(value: String?) {
    if (value.isNullOrEmpty()) {
        throw InvalidArgumentException(ErrorCode.E1002)
    }

    try {
        Hemisphere.valueOf(value)
    } catch (e: IllegalArgumentException) {
        throw InvalidArgumentException(ErrorCode.E1003)
    }
}