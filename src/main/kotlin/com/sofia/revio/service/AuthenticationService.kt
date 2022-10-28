package com.sofia.revio.service

import com.sofia.revio.model.request.UserBasic
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val userService: UserService
) {

    fun validate(authorization: String): UserBasic {
        val user = userService.validateToken(authorization).user
        return UserBasic(
            user.id!!, user.username, user.name, user.email, user.role
        )
    }

}
