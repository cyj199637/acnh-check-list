package com.nook.user.domain

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow

object Users: LongIdTable("user") {
    val name: Column<String> = varchar("name", 20)
    // TODO: enumeration 적용
    val hemisphere: Column<String> = varchar("hemisphere", 10)

    fun toEntity(row: ResultRow) = User(
        row[Users.id].value,
        row[name],
        Hemisphere.valueOf(row[hemisphere])
    )
}

data class User(val id: Long, val name: String, val hemisphere: Hemisphere) {
    constructor(name: String, hemisphere: Hemisphere)
        : this(0, name, hemisphere)
}

enum class Hemisphere {
    NORTHERN,
    SOUTHERN
}