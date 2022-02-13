package com.nook.model

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

enum class Hemisphere {
    NORTHERN,
    SOUTHERN
}

object User: LongIdTable() {
    val name: Column<String> = varchar("name", 20)
    // TODO: enumeration 적용
    val hemisphere: Column<String> = varchar("hemisphere", 10)
}