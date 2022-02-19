package com.nook.user.dto

import com.nook.user.domain.Hemisphere
import com.nook.user.validateHemisphere
import kotlinx.serialization.Serializable

@Serializable
class UpdateUserRequest(val hemisphere: String) {
    init {
        validateHemisphere(hemisphere)
    }

    fun toHemisphere() = Hemisphere.valueOf(hemisphere)
}