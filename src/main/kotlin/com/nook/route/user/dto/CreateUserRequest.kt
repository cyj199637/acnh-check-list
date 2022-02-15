package com.nook.route.user.dto

import com.nook.global.ErrorCode
import com.nook.domain.Hemisphere
import com.nook.util.validate
import kotlinx.serialization.Serializable
import java.lang.IllegalArgumentException

@Serializable
data class CreateUserRequest(val name: String, val hemisphere: String) {
    init {
        validate(name.isNotEmpty(), ErrorCode.E0001)
        validate(name.length <= 20, ErrorCode.E0002)
        validate(hemisphere.isNotEmpty(), ErrorCode.E0003)
        validate(isHemisphere(hemisphere), ErrorCode.E0004)
    }
}

fun isHemisphere(value: String): Boolean {
    return try {
        Hemisphere.valueOf(value)
        true
    } catch (e: IllegalArgumentException) {
        false
    }
}