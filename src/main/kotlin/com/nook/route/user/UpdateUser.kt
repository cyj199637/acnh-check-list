package com.nook.route.user

import com.nook.route.user.dto.UpdateUserRequest
import com.nook.domain.User
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

fun Route.updateUser() {
    patch("{id}") {
        val id = call.parameters["id"]?.toLong() ?: return@patch call.respond(
            status = HttpStatusCode.BadRequest,
            "아이디가 누락되었습니다."
        )
        val updated = call.receive<UpdateUserRequest>()

        transaction {
            User.update({ User.id eq id }) {
                it[hemisphere] = updated.hemisphere
            }
        }

        call.respond(
            status = HttpStatusCode.OK,
            "정보가 성공적으로 수정되었습니다."
        )
    }
}