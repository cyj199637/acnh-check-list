package com.nook.user.dto

import com.nook.user.domain.Hemisphere
import com.nook.user.domain.User
import kotlinx.serialization.Serializable

@Serializable
class UserResponse(val id: Long, val name: String, val hemisphere: Hemisphere) {
    constructor(user: User)
        : this(user.id, user.name, user.hemisphere)
}