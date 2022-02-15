package com.nook.route.user

import com.nook.domain.User
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.deleteUser() {
    delete("{id}") {
        val id = call.parameters["id"]?.toLong() ?: return@delete call.respond(
            status = HttpStatusCode.BadRequest,
            "아이디가 누락되었습니다."
        )

        transaction {
            User.deleteWhere {
                User.id eq id
            }
        }

        call.respond(
            status = HttpStatusCode.OK,
            "탈퇴 처리가 성공적으로 되었습니다."
        )
    }
}