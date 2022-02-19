package com.nook.plugin

import com.nook.user.*
import io.ktor.routing.*
import io.ktor.application.*

fun Application.configureRouting() {
    routing {
        userRouting()
    }
}
