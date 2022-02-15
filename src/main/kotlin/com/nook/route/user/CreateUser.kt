package com.nook.route.user

import com.nook.route.user.dto.CreateUserRequest
import com.nook.domain.User
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.createUser() {
    post {
        val request = call.receive<CreateUserRequest>()
        val userId = transaction {
            User.insert {
                it[name] = request.name
                it[hemisphere] = request.hemisphere
            } get User.id
        }
        call.response.header(HttpHeaders.Location, "/users/${userId}")
        call.respond(status = HttpStatusCode.Created, "사용자가 성공적으로 등록되었습니다.")
    }
}