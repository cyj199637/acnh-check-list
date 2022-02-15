package com.nook.route.user.dto

import com.nook.global.ErrorCode
import com.nook.util.validate
import kotlinx.serialization.Serializable

@Serializable
class UpdateUserRequest(val hemisphere: String) {
    init {
        validate(hemisphere.isNotEmpty(), ErrorCode.E0003)
        validate(isHemisphere(hemisphere), ErrorCode.E0004)
    }
}