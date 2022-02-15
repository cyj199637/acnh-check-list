package com.nook.global

import kotlinx.serialization.Serializable

@Serializable
class ApiError {
    private val error: Body
    constructor(errorCode: ErrorCode) {
        this.error = Body(errorCode.name, errorCode.message)
    }

    @Serializable
    class Body(private val code: String, private val message: String)
}