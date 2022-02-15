package com.nook.route.user

import com.nook.domain.User
import com.nook.route.user.dto.UserResponse
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.findUser() {
    // TODO: Exception Handling
    get("{id}") {
        val id = call.parameters["id"]?.toLong() ?: return@get call.respond(
            status = HttpStatusCode.BadRequest,
            "아이디가 누락되었습니다."
        )

        // TODO: ResultRow 자동 변환
        val row = transaction {
            User.select { User.id eq id }.firstOrNull()
        } ?: return@get call.respond(
            status = HttpStatusCode.NotFound,
            "사용자를 찾을 수 없습니다."
        )

        call.respond(UserResponse(row[User.id].value, row[User.name], row[User.hemisphere]))
    }
}