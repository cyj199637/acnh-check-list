package com.nook

import com.nook.database.DatabaseInitializer
import com.nook.global.ApiError
import com.nook.global.ServiceException
import io.ktor.application.*
import com.nook.plugin.*
import io.ktor.config.*
import io.ktor.features.*
import io.ktor.response.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    install(StatusPages) {
        exception<ServiceException> { cause ->
            call.respond(cause.statusCode, ApiError(cause.errorCode))
        }
    }
    configureRouting()
    configureSerialization()
    configureMonitoring()

    DatabaseInitializer.init(profileConfiguration(environment))
}

private fun profileConfiguration(environment: ApplicationEnvironment): ApplicationConfig {
    val env = environment.config.property("ktor.environment").getString()
    return when (env) {
        "development" -> environment.config.config("development")
        else -> environment.config.config("local")
    }
}
