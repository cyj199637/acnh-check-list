package com.nook.plugin

import com.nook.route.userRouting
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.response.*

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        userRouting()
    }
}
