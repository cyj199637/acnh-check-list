package com.nook.user

import com.nook.global.ErrorCode
import com.nook.global.ServiceException
import com.nook.user.domain.Hemisphere
import com.nook.user.domain.User
import com.nook.user.domain.Users
import io.ktor.http.*
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

fun create(user: User): Long {
    return transaction {
        Users.insertAndGetId {
            it[name] = user.name
            it[hemisphere] = user.hemisphere.name
        }.value
    }
}

fun findById(id: Long): User {
    val row = transaction {
        Users.select { Users.id eq id }.firstOrNull()
    } ?: throw ServiceException(HttpStatusCode.NotFound, ErrorCode.E1005)
    return Users.toEntity(row)
}

fun update(id: Long, hemisphere: Hemisphere) {
    transaction {
        Users.update({ Users.id eq id }) {
            it[Users.hemisphere] = hemisphere.name
        }
    }
}

fun delete(id: Long) {
    transaction {
        Users.deleteWhere {
            Users.id eq id
        }
    }
}