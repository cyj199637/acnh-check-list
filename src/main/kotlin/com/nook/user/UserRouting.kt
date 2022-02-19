package com.nook.user

import com.nook.global.ErrorCode
import com.nook.global.ServiceException
import com.nook.user.dto.CreateUserRequest
import com.nook.user.dto.UpdateUserRequest
import com.nook.user.dto.UserResponse
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.userRouting() {
    route("/users") {
        post {
            val request = call.receive<CreateUserRequest>()
            val userId = create(request.toUser())

            call.response.header(HttpHeaders.Location, "/users/${userId}")
            call.respond(status = HttpStatusCode.Created, "사용자가 성공적으로 등록되었습니다.")
        }

        get("{id}") {
            val id = call.parameters["id"]?.toLong() ?: throw ServiceException(HttpStatusCode.BadRequest, ErrorCode.E1004)
            val user = findById(id)

            call.respond(UserResponse(user))
        }

        patch("{id}") {
            val id = call.parameters["id"]?.toLong() ?: throw ServiceException(HttpStatusCode.BadRequest, ErrorCode.E1004)
            val updated = call.receive<UpdateUserRequest>()

            update(id, updated.toHemisphere())

            call.respond(status = HttpStatusCode.OK, "정보가 성공적으로 수정되었습니다.")
        }

        delete("{id}") {
            val id = call.parameters["id"]?.toLong() ?: throw ServiceException(HttpStatusCode.BadRequest, ErrorCode.E1004)

            delete(id)

            call.respond(status = HttpStatusCode.OK, "탈퇴 처리가 성공적으로 되었습니다.")
        }
    }
}