package com.nook.domain

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column

enum class Hemisphere {
    NORTHERN,
    SOUTHERN
}

object User: LongIdTable() {
    val name: Column<String> = varchar("name", 20)
    // TODO: enumeration 적용
    val hemisphere: Column<String> = varchar("hemisphere", 10)
}