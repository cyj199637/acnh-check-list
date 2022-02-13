package com.nook

import com.nook.database.DatabaseInitializer
import io.ktor.application.*
import com.nook.plugin.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureRouting()
    configureSerialization()
    configureMonitoring()

    DatabaseInitializer.init(this.environment.config)
}
