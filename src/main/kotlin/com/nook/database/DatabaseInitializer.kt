package com.nook.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.config.*
import org.jetbrains.exposed.sql.Database

// TODO: java.io.ioexception: connection reset by peer
object DatabaseInitializer {
    fun init(config: ApplicationConfig) {
        Database.connect(hikariDatasource(config))
    }
}

private fun hikariDatasource(config: ApplicationConfig) = HikariDataSource(
    HikariConfig().apply {
        jdbcUrl = config.property("database.url").getString()
        driverClassName = config.property("database.driver").getString()
        username = config.property("database.username").getString()
        password = config.property("database.password").getString()
        maximumPoolSize = 10
        isAutoCommit = false
        transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        validate()
    }
)