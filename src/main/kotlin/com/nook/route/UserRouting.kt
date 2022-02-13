package com.nook.route

import com.nook.model.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

fun Route.userRouting() {
    route("/users") {
        post {
            val request = call.receive<CreateUserRequest>()
            transaction {
                val userId = User.insert {
                    it[name] = request.name
                    it[hemisphere] = request.hemisphere.name
                } get User.id
            }
            call.respond(status = HttpStatusCode.Created, "사용자가 성공적으로 등록되었습니다.")
        }

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

        patch("{id}") {
            val id = call.parameters["id"]?.toLong() ?: return@patch call.respond(
                status = HttpStatusCode.BadRequest,
                "아이디가 누락되었습니다."
            )
            val updated = call.receive<UpdateUserRequest>()

            transaction {
                User.update({ User.id eq id }) {
                    it[hemisphere] = updated.hemisphere.name
                }
            }

            call.respond(
                status = HttpStatusCode.OK,
                "정보가 성공적으로 수정되었습니다."
            )
        }

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
}

