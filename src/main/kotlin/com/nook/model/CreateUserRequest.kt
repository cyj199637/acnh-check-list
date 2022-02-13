package com.nook.model

import kotlinx.serialization.Serializable

@Serializable
// TODO: 유효성 검증
data class CreateUserRequest(val name: String, val hemisphere: Hemisphere)