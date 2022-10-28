package com.sofia.revio.service

import com.sofia.revio.model.request.UserBasic
import org.jboss.logging.Logger
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val userService: UserService
) {
    val logger: Logger = Logger.getLogger(this.javaClass.name)
    fun validate(authorization: String): UserBasic {
        println("AuthenticationService, validate:${authorization}")
        val user = userService.validateToken(authorization).user

        return UserBasic(
            user.id!!, user.username, user.name, user.email, user.role
        )
    }

}
