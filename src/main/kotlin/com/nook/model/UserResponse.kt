package com.nook.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ResultRow

@Serializable
class UserResponse(val id: Long, val name: String, val hemisphere: String)