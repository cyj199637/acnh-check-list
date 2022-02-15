package com.nook.plugin

import com.nook.route.user.*
import io.ktor.routing.*
import io.ktor.application.*

fun Application.configureRouting() {
    routing {
        route("/users") {
            createUser()
            findUser()
            updateUser()
            deleteUser()
        }
    }
}
