package com.nook.user.dto

import com.nook.user.domain.Hemisphere
import com.nook.user.domain.User
import com.nook.user.validateHemisphere
import com.nook.user.validateName
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequest(val name: String, val hemisphere: String) {
    init {
        validateName(name)
        validateHemisphere(hemisphere)
    }

    fun toUser() = User(name, Hemisphere.valueOf(hemisphere))
}